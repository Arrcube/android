package dev.agustacandi.learn.pestsentry.ui.profile

import androidx.lifecycle.ViewModel
import dev.agustacandi.learn.pestsentry.data.auth.AuthRepository

class ProfileViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun logout() = authRepository.logout()
}