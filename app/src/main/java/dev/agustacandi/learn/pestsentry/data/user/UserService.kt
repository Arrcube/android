package dev.agustacandi.learn.pestsentry.data.user

import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path
interface UserService {
    @PATCH("users/password/{id}")
    suspend fun change(
        @Path("id") id: String,
        @Body changePasswordRequest: ChangePasswordRequest
    ) : ChangeResponse
}