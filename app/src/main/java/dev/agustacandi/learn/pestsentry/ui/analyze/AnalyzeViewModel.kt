package dev.agustacandi.learn.pestsentry.ui.analyze

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.data.predict.dto.PredictResponse
import dev.agustacandi.learn.pestsentry.data.predict.repository.PredictRepository
import kotlinx.coroutines.launch

class AnalyzeViewModel(private val predictRepository: PredictRepository) : ViewModel() {
    private val _analyzeImageResult = MutableLiveData<ApiResponse<PredictResponse>?>()
    val analyzeImageResult: LiveData<ApiResponse<PredictResponse>?> by lazy { _analyzeImageResult }

    fun predictPest(imageUri: Uri) {
        viewModelScope.launch {
            predictRepository.predictPest(imageUri).collect {
                _analyzeImageResult.value = it
            }
        }
    }

    fun setToNull() {
        _analyzeImageResult.value = null
    }
}