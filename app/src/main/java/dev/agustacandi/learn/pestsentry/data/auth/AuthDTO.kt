package dev.agustacandi.learn.pestsentry.data.auth

import com.google.gson.annotations.SerializedName
import dev.agustacandi.learn.pestsentry.data.lib.BaseResponse

// Login Data Class
data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("token")
    val token: String
) : BaseResponse()


// Register Data Class
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

class RegisterResponse : BaseResponse()