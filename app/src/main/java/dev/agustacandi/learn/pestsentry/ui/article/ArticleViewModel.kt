package dev.agustacandi.learn.pestsentry.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.news.dto.ArticlesItem
import dev.agustacandi.learn.pestsentry.data.news.dto.NewsResponse
import dev.agustacandi.learn.pestsentry.data.news.repository.NewsRepository
import kotlinx.coroutines.launch

class ArticleViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    private val _articleResult = MutableLiveData<ApiResponse<NewsResponse>>()
    val articleResult: LiveData<ApiResponse<NewsResponse>> by lazy { _articleResult }

    fun getNews(query: String = "Pest") {
        viewModelScope.launch {
            newsRepository.getNews(query).collect {
                _articleResult.value = it
            }
        }
    }
}