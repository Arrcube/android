package dev.agustacandi.learn.pestsentry.data.history.service

import dev.agustacandi.learn.pestsentry.data.history.dto.BaseHistoryResponse
import dev.agustacandi.learn.pestsentry.data.history.dto.DetailHistoryResponse
import dev.agustacandi.learn.pestsentry.data.history.dto.HistoryResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface HistoryService {
    @GET("predict/histories")
    suspend fun getHistories(): HistoryResponse

    @GET("predict/histories/{id}")
    suspend fun getHistoryById(
        @Path("id") id: String
    ): DetailHistoryResponse

    @DELETE("predict/histories")
    suspend fun deleteHistories(): BaseHistoryResponse

    @DELETE("predict/histories/{id}")
    suspend fun deleteHistoryById(
        @Path("id") id: String
    ): BaseHistoryResponse

}