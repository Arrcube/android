package dev.agustacandi.learn.pestsentry.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.databinding.FragmentArticleBinding
import dev.agustacandi.learn.pestsentry.ui.home.ArticleAdapter
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import dev.agustacandi.learn.pestsentry.utils.ext.show
import org.koin.android.ext.android.inject

class ArticleFragment : BaseFragment<FragmentArticleBinding>() {

    private val articleViewModel: ArticleViewModel by inject()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentArticleBinding = FragmentArticleBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
        binding.apply {
            appbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            searchView.editText.setOnEditorActionListener { _, _, _ ->
                val usernameQuery =
                    if (searchView.text.isEmpty()) "Pest" else searchView.text.toString()
                searchView.hide()
                searchBar.setText(searchView.text)
                articleViewModel.getNews(usernameQuery)
                false
            }
        }
    }

    override fun initProcess() {
        articleViewModel.getNews()
    }

    override fun initObservers() {
        articleViewModel.articleResult.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when(result) {
                    is ApiResponse.Loading -> {
                        shimmerArticles.root.show()
                        shimmerArticles.root.startShimmer()
                        rvArticle.gone()
                    }
                    is ApiResponse.Success -> {
                        shimmerArticles.root.gone()
                        shimmerArticles.root.stopShimmer()
                        rvArticle.show()

                        val adapter = ArticleAdapter(
                            navDirections = {
                                ArticleFragmentDirections.actionArticleFragmentToDetailArticleFragment(it)
                            }
                        )
                        val layoutManager = LinearLayoutManager(requireActivity())
                        val article = result.data.articles.filter { it.urlToImage != null }
                        adapter.submitList(article)
                        rvArticle.layoutManager = layoutManager
                        rvArticle.adapter = adapter
                    }
                    is ApiResponse.Error -> {
                        shimmerArticles.root.gone()
                        shimmerArticles.root.stopShimmer()
                        Helper.showErrorToast(requireActivity(), result.errorMessage)
                    }
                }
            }
        }
    }

}