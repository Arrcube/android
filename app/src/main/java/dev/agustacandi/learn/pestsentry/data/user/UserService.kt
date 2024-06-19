package dev.agustacandi.learn.pestsentry.data.user

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.Path
interface UserService {
    @PATCH("users/password/{id}")
    suspend fun change(
        @Path("id") id: String,
        @Body changePasswordRequest: ChangePasswordRequest
    ) : ChangeResponse

    @PATCH("users/email/{id}")
    suspend fun editEmail(
        @Path("id") id: String,
        @Body changeEmailRequest: ChangeEmailRequest
    ) : ChangeResponse

    @PATCH("users/username/{id}")
    suspend fun editUsername(
        @Path("id") id: String,
        @Body changeUsernameRequest: ChangeUsernameRequest
    ) : ChangeResponse

    @DELETE("users/{id}")
    suspend fun deleteAccount(
        @Path("id") id: String,
    ) : ChangeResponse
}