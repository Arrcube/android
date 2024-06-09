package dev.agustacandi.learn.pestsentry.data.news.repository

import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.news.service.NewsService
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(private val newsService: NewsService) : NewsRepository {
    override suspend fun getNews(query: String) = flow {
        try {
            emit(ApiResponse.Loading)
            val response = newsService.getNews(query)
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