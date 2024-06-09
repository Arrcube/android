package dev.agustacandi.learn.pestsentry.data.lib

import com.google.gson.annotations.SerializedName

abstract class BaseResponse(

    @field:SerializedName("error")
    val error: Boolean = false,

    @field:SerializedName("messages")
    val messages: String = ""
)