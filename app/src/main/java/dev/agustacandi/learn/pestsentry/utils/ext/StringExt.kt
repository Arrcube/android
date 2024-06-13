package dev.agustacandi.learn.pestsentry.utils.ext

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun String.formatDate(): String {
    val isoString = this
    val instant = Instant.parse(isoString)
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
    val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val formattedDate = dateTime.format(formatter)
    return formattedDate.toString()
}