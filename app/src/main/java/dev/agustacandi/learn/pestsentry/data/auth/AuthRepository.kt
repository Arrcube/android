package dev.agustacandi.learn.pestsentry.data.auth

import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(dto: LoginRequest): Flow<ApiResponse<LoginResponse>>
    fun register(dto: RegisterRequest): Flow<ApiResponse<RegisterResponse>>
    fun logout(): Boolean
}