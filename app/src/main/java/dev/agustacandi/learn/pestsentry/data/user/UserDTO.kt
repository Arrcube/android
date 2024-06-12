package dev.agustacandi.learn.pestsentry.data.user

import com.google.gson.annotations.SerializedName

data class ChangeResponse(
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String
)

data class ChangePasswordRequest(
    val newPassword: String,
)

data class ChangeEmailRequest(
    val newEmail: String,
)

data class ChangeUsernameRequest(
    val newUsername: String,
)