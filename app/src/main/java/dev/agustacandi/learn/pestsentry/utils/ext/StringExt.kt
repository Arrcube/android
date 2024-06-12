package dev.agustacandi.learn.pestsentry.utils.ext

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.formatDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val dateTime = LocalDateTime.parse(this, formatter)
    val formattedDate = dateTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
    return formattedDate.toString()
}