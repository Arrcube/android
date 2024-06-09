package dev.agustacandi.learn.pestsentry.data.predict.dto

import com.google.gson.annotations.SerializedName

data class PredictResponse (
    @field:SerializedName("prediction") val prediction: String
)