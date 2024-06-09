package dev.agustacandi.learn.pestsentry.data.news.service

import dev.agustacandi.learn.pestsentry.BuildConfig
import dev.agustacandi.learn.pestsentry.data.news.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String = "Pest",
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): NewsResponse
}