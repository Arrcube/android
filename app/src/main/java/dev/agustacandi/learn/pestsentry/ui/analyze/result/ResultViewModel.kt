package dev.agustacandi.learn.pestsentry.ui.analyze.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.news.dto.NewsResponse
import dev.agustacandi.learn.pestsentry.data.news.repository.NewsRepository
import dev.agustacandi.learn.pestsentry.data.news.service.NewsService
import kotlinx.coroutines.launch

class ResultViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    private val _articleResult = MutableLiveData<ApiResponse<NewsResponse>>()
    val articleResult: LiveData<ApiResponse<NewsResponse>> by lazy { _articleResult }

    fun getNews(query: String) {
        viewModelScope.launch {
            newsRepository.getNews(query).collect {
                _articleResult.value = it
            }
        }
    }
}