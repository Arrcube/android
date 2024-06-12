package dev.agustacandi.learn.pestsentry.ui.profile.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.user.ChangeResponse
import dev.agustacandi.learn.pestsentry.data.user.UserRepository
import kotlinx.coroutines.launch

class EditProfileViewModel(private val userRepository : UserRepository) : ViewModel() {
    private val _editResult = MutableLiveData<ApiResponse<ChangeResponse>>()
    val editResult: LiveData<ApiResponse<ChangeResponse>> by lazy { _editResult }

    fun editProfile(newEmail : String, newUsername : String){
        viewModelScope.launch {
            userRepository.edit(newEmail,newUsername).collect{
                _editResult.value = it
            }
        }
    }

    fun logout() = userRepository.logout()
}