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
    val token: String,
    @field:SerializedName("user")
    val user: User
) : BaseResponse()

data class User(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String
)
// Register Data Class
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: Data
) : BaseResponse()

data class Data(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String
)