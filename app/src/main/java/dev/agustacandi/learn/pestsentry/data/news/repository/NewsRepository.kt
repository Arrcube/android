package dev.agustacandi.learn.pestsentry.data.news.repository

import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.news.dto.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(query: String): Flow<ApiResponse<NewsResponse>>
}