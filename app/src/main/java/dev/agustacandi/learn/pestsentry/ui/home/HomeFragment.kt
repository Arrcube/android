package dev.agustacandi.learn.pestsentry.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.databinding.FragmentHomeBinding
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.PreferenceManager
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import dev.agustacandi.learn.pestsentry.utils.ext.show
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by inject()
    private val preferenceManager : PreferenceManager by inject()
    private var listImage: ArrayList<Int> = arrayListOf()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
        listImage.add(R.drawable.carousel_1)
        listImage.add(R.drawable.carousel_2)
        listImage.add(R.drawable.carousel_3)

        val adapter = ImageAdapter(listImage)

        binding.apply {
            tvHomeUsername.text = preferenceManager.getName
            rvImage.adapter = adapter
            rvImage.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
            val snapHelper = CarouselSnapHelper()
            snapHelper.attachToRecyclerView(rvImage)
        }
    }

    override fun initAction() {
        binding.apply {
            seeAllButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToArticleFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun initProcess() {
        homeViewModel.getNews()
    }

    override fun initObservers() {
        homeViewModel.articleResult.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when(result) {
                    is ApiResponse.Loading -> {
                        shimmerArticles.root.show()
                        shimmerArticles.root.startShimmer()
                    }
                    is ApiResponse.Success -> {
                        shimmerArticles.root.gone()
                        shimmerArticles.root.stopShimmer()
                        val adapter = ArticleAdapter(navDirections = {
                            HomeFragmentDirections.actionHomeFragmentToDetailArticleFragment(it)
                        })
                        val layoutManager = LinearLayoutManager(requireActivity())
                        val article = result.data.articles.take(5)
                        adapter.submitList(article)
                        recyclerView.layoutManager = layoutManager
                        recyclerView.adapter = adapter
                    }
                    is ApiResponse.Error -> {
                        if (result.errorMessage.contains("timeout")) {
                            errorTimeoutLayout.root.show()
                        }
                        shimmerArticles.root.gone()
                        shimmerArticles.root.stopShimmer()
                        Helper.showErrorToast(requireActivity(), result.errorMessage)
                    }
                }
            }
        }
    }

}