package dev.agustacandi.learn.pestsentry.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.agustacandi.learn.pestsentry.data.auth.AuthRepository
import dev.agustacandi.learn.pestsentry.data.auth.RegisterRequest
import dev.agustacandi.learn.pestsentry.data.auth.RegisterResponse
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import kotlinx.coroutines.launch

class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _registerResult = MutableLiveData<ApiResponse<RegisterResponse>>()
    val registerResult: LiveData<ApiResponse<RegisterResponse>> by lazy { _registerResult }

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.register(RegisterRequest(username, email, password)).collect {
                _registerResult.value = it
            }
        }
    }
}