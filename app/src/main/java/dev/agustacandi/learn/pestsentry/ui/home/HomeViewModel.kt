package dev.agustacandi.learn.pestsentry.ui.home

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.news.dto.NewsResponse
import dev.agustacandi.learn.pestsentry.data.news.repository.NewsRepository
import dev.agustacandi.learn.pestsentry.data.predict.dto.PredictResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    private val _articleResult = MutableLiveData<ApiResponse<NewsResponse>>()
    val articleResult: LiveData<ApiResponse<NewsResponse>> by lazy { _articleResult }

    fun getNews() {
        viewModelScope.launch {
            newsRepository.getNews(pageSize = 3).collect {
                _articleResult.value = it
            }
        }
    }
}