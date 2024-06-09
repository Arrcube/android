package dev.agustacandi.learn.pestsentry.utils.ext

import android.widget.Button
import androidx.core.content.ContextCompat
import dev.agustacandi.learn.pestsentry.R

fun Button.setDisable() {
    isEnabled = false
    background = ContextCompat.getDrawable(context, R.drawable.corner_disabled)
    setTextColor(ContextCompat.getColor(context, R.color.black))
}

fun Button.setEnable() {
    isEnabled = true
    background = ContextCompat.getDrawable(context, R.drawable.corner_border)
    setTextColor(ContextCompat.getColor(context, R.color.white))
}