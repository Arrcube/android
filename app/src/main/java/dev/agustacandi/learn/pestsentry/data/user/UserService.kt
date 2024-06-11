package dev.agustacandi.learn.pestsentry.data.user

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PATCH
import retrofit2.http.Path
interface UserService {
    @FormUrlEncoded
    @PATCH("users/password/{id}")
    fun change(
        @Path("id") userId: String,
        @Field("newPassword") newPassword : String
    ) : ChangeResponse
}