package dev.agustacandi.learn.pestsentry.data.user

import dev.agustacandi.learn.pestsentry.data.auth.LoginRequest
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.utils.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//this class is called in UserModule.kt
class UserRepositoryImpl(
    private val userService: UserService,
    private val preferenceManager: PreferenceManager
) : UserRepository {
    override fun change(newPassword: String): Flow<ApiResponse<ChangeResponse>> = flow {
        try {
            emit(ApiResponse.Loading)
            //get user id from preference
            val id = preferenceManager.getId
            val response = userService.change(id!!, newPassword)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.Error(e.message.toString()))
        }
    }

}