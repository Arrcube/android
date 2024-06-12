package dev.agustacandi.learn.pestsentry.ui.analyze.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.databinding.FragmentHistoryBinding
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import dev.agustacandi.learn.pestsentry.utils.ext.show
import org.koin.android.ext.android.inject

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val historyViewModel: HistoryViewModel by inject()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHistoryBinding = FragmentHistoryBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
        binding.apply {
            appbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun initProcess() {
        historyViewModel.getHistories()
    }

    override fun initObservers() {
        historyViewModel.historyResult.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when(result) {
                    is ApiResponse.Loading -> {
                        shimmerArticles.root.show()
                        noHistoriesFoundText.gone()
                        shimmerArticles.root.startShimmer()
                        rvHistory.gone()
                    }
                    is ApiResponse.Success -> {
                        shimmerArticles.root.gone()
                        shimmerArticles.root.stopShimmer()

                        if (result.data.histories.isEmpty()) {
                            noHistoriesFoundText.show()
                        } else {
                            rvHistory.show()
                            val adapter = HistoryAdapter()
                            val layoutManager = LinearLayoutManager(requireActivity())
                            val histories = result.data.histories
                            adapter.submitList(histories)
                            rvHistory.layoutManager = layoutManager
                            rvHistory.adapter = adapter
                        }
                    }
                    is ApiResponse.Error -> {
                        shimmerArticles.root.gone()
                        noHistoriesFoundText.gone()
                        shimmerArticles.root.stopShimmer()
                        Helper.showErrorToast(requireActivity(), result.errorMessage)
                    }
                }
            }
        }
    }

}