package dev.agustacandi.learn.pestsentry.data.user

import com.google.gson.annotations.SerializedName
import dev.agustacandi.learn.pestsentry.data.lib.BaseResponse

data class ChangeResponse(
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String
)

data class ChangePasswordRequest(
    val newPassword: String,
)