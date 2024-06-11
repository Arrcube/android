package dev.agustacandi.learn.pestsentry.ui.profile.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.user.ChangeResponse
import dev.agustacandi.learn.pestsentry.data.user.UserRepository
import kotlinx.coroutines.launch

class PasswordViewModel(private val userRepository : UserRepository) : ViewModel() {
    private val _changeResult = MutableLiveData<ApiResponse<ChangeResponse>>()
    val changeResult: LiveData<ApiResponse<ChangeResponse>> by lazy { _changeResult }

    fun change(newPassword : String){
        viewModelScope.launch {
            userRepository.change(newPassword).collect{
                _changeResult.value = it
            }
        }
    }
}