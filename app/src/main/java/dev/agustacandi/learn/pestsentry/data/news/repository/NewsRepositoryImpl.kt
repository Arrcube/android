package dev.agustacandi.learn.pestsentry.data.news.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSourceFactory
import androidx.paging.liveData
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.news.dto.ArticlesItem
import dev.agustacandi.learn.pestsentry.data.news.dto.NewsResponse
import dev.agustacandi.learn.pestsentry.data.news.service.NewsService
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(private val newsService: NewsService) : NewsRepository {
    override suspend fun getNews(query: String, pageSize: Int) = flow {
        try {
            emit(ApiResponse.Loading)
            val response = newsService.getNews(query = query, pageSize = pageSize)
            if (response.status == "ok" && response.articles.isNotEmpty()) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Error("Article not found"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.Error(e.message.toString()))
        }
    }
}