package dev.agustacandi.learn.pestsentry.data.history.repository

import dev.agustacandi.learn.pestsentry.data.history.dto.BaseHistoryResponse
import dev.agustacandi.learn.pestsentry.data.history.dto.DetailHistoryResponse
import dev.agustacandi.learn.pestsentry.data.history.dto.HistoryResponse
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun getHistories(): Flow<ApiResponse<HistoryResponse>>

    suspend fun getHistoryById(id: String): Flow<ApiResponse<DetailHistoryResponse>>

    suspend fun deleteHistories(): Flow<ApiResponse<BaseHistoryResponse>>

    suspend fun deleteHistoryById(id: String): Flow<ApiResponse<BaseHistoryResponse>>
}