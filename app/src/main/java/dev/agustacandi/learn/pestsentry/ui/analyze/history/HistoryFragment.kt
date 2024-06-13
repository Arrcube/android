package dev.agustacandi.learn.pestsentry.ui.analyze.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.data.history.dto.HistoriesItem
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.databinding.FragmentHistoryBinding
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.PreferenceManager
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import dev.agustacandi.learn.pestsentry.utils.ext.show
import dev.agustacandi.learn.pestsentry.utils.ext.showConfirmDialog
import dev.agustacandi.learn.pestsentry.utils.ext.showSessionDialog
import org.koin.android.ext.android.inject

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val historyViewModel: HistoryViewModel by inject()
    private val preferenceManager: PreferenceManager by inject()

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

            deleteHistoriesButton.setOnClickListener {
                showConfirmDialog(
                    title = getString(R.string.are_you_sure),
                    message = "",
                    positiveButtonText = getString(R.string.yes),
                    negativeButtonText = getString(R.string.no),
                    onPositiveClick = {
                        historyViewModel.deleteHistories()
                    }
                )
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
                    is ApiResponse.Loading -> setLoading()

                    is ApiResponse.Success -> {
                        shimmerArticles.root.gone()
                        shimmerArticles.root.stopShimmer()

                        if (result.data.histories.isEmpty()) {
                            noHistoriesFoundText.show()
                        } else {
                            rvHistory.show()
                            val histories = result.data.histories
                            setupRecyclerView(histories)
                        }
                    }

                    is ApiResponse.Error -> {
                        if (result.errorMessage.contains("401")) {
                            showSessionDialog(
                                onClick = {
                                    try {
                                        preferenceManager.clearAllPreferences()
                                        Helper.reloadKoinModules()
                                        findNavController().navigate(R.id.action_historyFragment_to_loginFragment)
                                    } catch (e: Exception) {
                                        Helper.showErrorToast(requireActivity(), e.message.toString())
                                    }
                                }
                            )
                        }
                       hideLoading()
                        Helper.showErrorToast(requireActivity(), result.errorMessage)
                    }
                }
            }
        }

        historyViewModel.deleteHistoryByIdResult.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when(result) {
                    is ApiResponse.Loading -> setLoading()

                    is ApiResponse.Success -> {
                        historyViewModel.getHistories()
                    }

                    is ApiResponse.Error -> {
                        hideLoading()
                        Helper.showErrorToast(requireActivity(), result.errorMessage)
                    }
                }
            }
        }

        historyViewModel.deleteHistoriesResult.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when(result) {
                    is ApiResponse.Loading -> setLoading()

                    is ApiResponse.Success -> {
                        historyViewModel.getHistories()
                    }

                    is ApiResponse.Error -> {
                        hideLoading()
                        Helper.showErrorToast(requireActivity(), result.errorMessage)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView(histories: List<HistoriesItem>) {
        val adapter = HistoryAdapter(this@HistoryFragment, historyViewModel)
        val layoutManager = LinearLayoutManager(requireActivity())
        adapter.submitList(histories)
        binding.rvHistory.layoutManager = layoutManager
        binding.rvHistory.adapter = adapter
    }

    private fun setLoading() {
        binding.apply {
            shimmerArticles.root.show()
            noHistoriesFoundText.gone()
            shimmerArticles.root.startShimmer()
            rvHistory.gone()
        }
    }

    private fun hideLoading() {
        binding.apply {
            shimmerArticles.root.gone()
            noHistoriesFoundText.gone()
            shimmerArticles.root.stopShimmer()
            rvHistory.show()
        }
    }
}