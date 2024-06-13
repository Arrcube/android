package dev.agustacandi.learn.pestsentry.data.history.repository

import dev.agustacandi.learn.pestsentry.data.history.dto.BaseHistoryResponse
import dev.agustacandi.learn.pestsentry.data.history.dto.DetailHistoryResponse
import dev.agustacandi.learn.pestsentry.data.history.dto.HistoryResponse
import dev.agustacandi.learn.pestsentry.data.history.service.HistoryService
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryRepositoryImpl(private val historyService: HistoryService) : HistoryRepository {
    override suspend fun getHistories(): Flow<ApiResponse<HistoryResponse>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = historyService.getHistories()
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }

    override suspend fun getHistoryById(id: String): Flow<ApiResponse<DetailHistoryResponse>> = flow {

    }

    override suspend fun deleteHistories(): Flow<ApiResponse<BaseHistoryResponse>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = historyService.deleteHistories()
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }

    override suspend fun deleteHistoryById(id: String): Flow<ApiResponse<BaseHistoryResponse>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = historyService.deleteHistoryById(id)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }
}