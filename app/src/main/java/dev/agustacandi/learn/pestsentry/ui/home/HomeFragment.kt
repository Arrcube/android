package dev.agustacandi.learn.pestsentry.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.databinding.FragmentHomeBinding
import dev.agustacandi.learn.pestsentry.utils.PreferenceManager
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by inject()
    private val preferenceManager : PreferenceManager by inject()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
        binding.apply {
            tvHomeUsername.text = preferenceManager.getName
        }
    }

    override fun initAction() {
    }

    override fun initProcess() {
        homeViewModel.getNews()
    }

    override fun initObservers() {
        homeViewModel.articleResult.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when(result) {
                    is ApiResponse.Loading -> {}
                    is ApiResponse.Success -> {
                        val adapter = ArticleAdapter()
                        val layoutManager = LinearLayoutManager(requireActivity())
                        val article = result.data.articles.take(3)
                        adapter.submitList(article)
                        recyclerView.layoutManager = layoutManager
                        recyclerView.adapter = adapter
                    }
                    is ApiResponse.Error -> {}
                }
            }
        }
    }

}