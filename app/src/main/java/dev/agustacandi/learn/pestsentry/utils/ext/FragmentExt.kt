package dev.agustacandi.learn.pestsentry.utils.ext

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.PreferenceManager

fun Fragment.showConfirmDialog(
    title: String,
    message: String,
    positiveButtonText: String,
    onPositiveClick:() -> Unit,
    negativeButtonText: String,
){
    AlertDialog.Builder(requireContext()).apply {
        setTitle(title)
        setMessage(message)
        setNegativeButton(negativeButtonText) { p0, _ ->
            p0.dismiss()
        }
        setPositiveButton(positiveButtonText) { _, _ ->
            onPositiveClick()
        }
    }.create().show()
}

fun Fragment.showSessionDialog(
    onClick: () -> Unit
){
    AlertDialog.Builder(requireContext()).apply {
        setTitle(getString(R.string.token_expired))
        setMessage(R.string.token_expired_message)
        setPositiveButton(getString(R.string.yes)) { _, _ ->
            onClick()
        }
    }.create().show()
}