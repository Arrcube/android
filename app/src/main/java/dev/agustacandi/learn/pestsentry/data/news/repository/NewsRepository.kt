package dev.agustacandi.learn.pestsentry.data.news.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.news.dto.ArticlesItem
import dev.agustacandi.learn.pestsentry.data.news.dto.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(query: String = "Pest", pageSize: Int = 15): Flow<ApiResponse<NewsResponse>>

}