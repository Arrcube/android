package dev.agustacandi.learn.pestsentry.data.predict.dto

import com.google.gson.annotations.SerializedName

data class PredictResponse (
    @field:SerializedName("prediction") val prediction: String
)

data class PredictDiseaseResponse (
    @field:SerializedName("imageUrl") val imageUrl: String,
    @field:SerializedName("prediction") val prediction: String,
    @field:SerializedName("user_id") val userId: String
)