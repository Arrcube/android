package dev.agustacandi.learn.pestsentry.data.user

import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun change(newPassword : String) : Flow<ApiResponse<ChangeResponse>>
}