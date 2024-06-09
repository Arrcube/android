package dev.agustacandi.learn.pestsentry.data.predict.service

import dev.agustacandi.learn.pestsentry.data.predict.dto.PredictResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PredictService {
    @Multipart
    @POST("predict")
    suspend fun predictPest(
        @Part file: MultipartBody.Part
    ): PredictResponse
}