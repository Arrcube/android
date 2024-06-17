package dev.agustacandi.learn.pestsentry.data.predict.repository

import android.net.Uri
import androidx.core.net.toFile
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.predict.dto.PredictDiseaseResponse
import dev.agustacandi.learn.pestsentry.data.predict.dto.PredictResponse
import dev.agustacandi.learn.pestsentry.data.predict.service.PredictService
import dev.agustacandi.learn.pestsentry.utils.ext.reduceFileImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class PredictRepositoryImpl(private val predictService: PredictService) : PredictRepository {
    override suspend fun predictPest(imageUri: Uri): Flow<ApiResponse<PredictResponse>> = flow {
        try {
            emit(ApiResponse.Loading)
            val photo = imageUri.toFile().reduceFileImage()
            val requestImageFile = photo.asRequestBody("image/*".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData("file", photo.name, requestImageFile)
            val response = predictService.predictPest(multipartBody)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.Error(e.message.toString()))
        }
    }

    override suspend fun predictDisease(imageUri: Uri): Flow<ApiResponse<PredictDiseaseResponse>> = flow {
        try {
            emit(ApiResponse.Loading)
            val photo = imageUri.toFile().reduceFileImage()
            val requestImageFile = photo.asRequestBody("image/*".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData("file", photo.name, requestImageFile)
            val response = predictService.predictDisease(multipartBody)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.Error(e.message.toString()))
        }
    }
}