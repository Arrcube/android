package dev.agustacandi.learn.pestsentry.ui.analyze.result

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.databinding.FragmentResultBinding
import dev.agustacandi.learn.pestsentry.ui.home.ArticleAdapter
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import dev.agustacandi.learn.pestsentry.utils.ext.show
import org.koin.android.ext.android.inject

class ResultFragment : BaseFragment<FragmentResultBinding>() {

    private val resultViewModel: ResultViewModel by inject()
    private var pestImageValue: String? = null
    private var pestNameValue: String? = null
    private var fromPageValue: String? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)

    override fun initIntent() {
        pestImageValue = arguments?.getString("pestImage")
        pestNameValue = arguments?.getString("pestName")
        fromPageValue = arguments?.getString("fromPage")
    }

    override fun initUI() {
        binding.apply {
            pestImage.load(if (fromPageValue!! == getString(R.string.analyze)) pestImageValue!!.toUri() else pestImageValue)
            pestName.text = pestNameValue
        }
    }

    override fun initAction() {
        binding.apply {
            appbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun initProcess() {
        resultViewModel.getNews(pestNameValue ?: "Pest")
    }

    override fun initObservers() {
        resultViewModel.articleResult.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when(result) {
                    is ApiResponse.Loading -> {
                        shimmerArticles.root.show()
                        shimmerArticles.root.startShimmer()
                    }
                    is ApiResponse.Success -> {
                        shimmerArticles.root.gone()
                        shimmerArticles.root.stopShimmer()

                        val adapter = ArticleAdapter(
                            navDirections = {
                                ResultFragmentDirections.actionResultFragmentToDetailArticleFragment(it)
                            }
                        )
                        val layoutManager = LinearLayoutManager(requireActivity())
                        adapter.submitList(result.data.articles)
                        rvRelatedArticles.layoutManager = layoutManager
                        rvRelatedArticles.adapter = adapter
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