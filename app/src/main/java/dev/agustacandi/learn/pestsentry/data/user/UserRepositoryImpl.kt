package dev.agustacandi.learn.pestsentry.data.user

import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.utils.Helper
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
            val response = userService.change(id!!, ChangePasswordRequest(newPassword))
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.Error(e.message.toString()))
        }
    }

    override fun edit(newEmail: String, newUsername: String): Flow<ApiResponse<ChangeResponse>> = flow {
        try {
            emit(ApiResponse.Loading)
            val id = preferenceManager.getId
            userService.editEmail(id!!, ChangeEmailRequest(newEmail))
            val resUsername = userService.editUsername(id, ChangeUsernameRequest(newUsername))
            preferenceManager.updateEmailUsername(newEmail, newUsername)
            emit(ApiResponse.Success(resUsername))
        }catch (e:Exception){
            e.printStackTrace()
            emit(ApiResponse.Error(e.message.toString()))
        }
    }

    override fun deleteAccount(): Flow<ApiResponse<ChangeResponse>> = flow {
        try {
            emit(ApiResponse.Loading)
            //get user id from preference
            val id = preferenceManager.getId
            val response = userService.deleteAccount(id!!)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.Error(e.message.toString()))
        }
    }

    override fun logout(): Boolean {
        return try {
            preferenceManager.clearAllPreferences()
            Helper.reloadKoinModules()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}