package dev.agustacandi.learn.pestsentry.data.predict.repository

import android.net.Uri
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.predict.dto.PredictResponse
import kotlinx.coroutines.flow.Flow

interface PredictRepository {
    suspend fun predictPest(imageUri: Uri): Flow<ApiResponse<PredictResponse>>
}