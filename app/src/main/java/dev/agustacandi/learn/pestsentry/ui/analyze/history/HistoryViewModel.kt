package dev.agustacandi.learn.pestsentry.ui.analyze.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.agustacandi.learn.pestsentry.data.history.dto.HistoryResponse
import dev.agustacandi.learn.pestsentry.data.history.repository.HistoryRepository
import dev.agustacandi.learn.pestsentry.data.history.service.HistoryService
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.news.dto.NewsResponse
import kotlinx.coroutines.launch

class HistoryViewModel(private val historyRepository: HistoryRepository) : ViewModel() {
    private val _historyResult = MutableLiveData<ApiResponse<HistoryResponse>>()
    val historyResult: LiveData<ApiResponse<HistoryResponse>> by lazy { _historyResult }

    fun getHistories() {
        viewModelScope.launch {
            historyRepository.getHistories().collect {
                _historyResult.value = it
            }
        }
    }
}