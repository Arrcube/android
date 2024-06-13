package dev.agustacandi.learn.pestsentry.ui.analyze.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.agustacandi.learn.pestsentry.data.history.dto.BaseHistoryResponse
import dev.agustacandi.learn.pestsentry.data.history.dto.HistoryResponse
import dev.agustacandi.learn.pestsentry.data.history.repository.HistoryRepository
import dev.agustacandi.learn.pestsentry.data.history.service.HistoryService
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.news.dto.NewsResponse
import kotlinx.coroutines.launch

class HistoryViewModel(private val historyRepository: HistoryRepository) : ViewModel() {
    private val _historyResult = MutableLiveData<ApiResponse<HistoryResponse>>()
    val historyResult: LiveData<ApiResponse<HistoryResponse>> by lazy { _historyResult }

    private val _deleteHistoriesResult = MutableLiveData<ApiResponse<BaseHistoryResponse>>()
    val deleteHistoriesResult: LiveData<ApiResponse<BaseHistoryResponse>> by lazy { _deleteHistoriesResult }

    private val _deleteHistoryByIdResult = MutableLiveData<ApiResponse<BaseHistoryResponse>>()
    val deleteHistoryByIdResult: LiveData<ApiResponse<BaseHistoryResponse>> by lazy { _deleteHistoryByIdResult }

    fun getHistories() {
        viewModelScope.launch {
            historyRepository.getHistories().collect {
                _historyResult.value = it
            }
        }
    }

    fun deleteHistories() {
        viewModelScope.launch {
            historyRepository.deleteHistories().collect {
                _deleteHistoriesResult.value = it
            }
        }
    }

    fun deleteHistoryById(id: String) {
        viewModelScope.launch {
            historyRepository.deleteHistoryById(id).collect {
                _deleteHistoryByIdResult.value = it
            }
        }
    }
}