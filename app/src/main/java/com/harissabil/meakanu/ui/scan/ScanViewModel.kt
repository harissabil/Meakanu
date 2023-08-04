package com.harissabil.meakanu.ui.scan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.harissabil.meakanu.data.PlantRepository
import com.harissabil.meakanu.data.local.entity.PlantEntity
import com.harissabil.meakanu.data.remote.response.plantnet.ImageResponsePlantnet
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class ScanViewModel(private val repository: PlantRepository) : ViewModel() {

    private val _errorResponse = MutableLiveData<ImageResponsePlantnet>()
    val errorResponse: LiveData<ImageResponsePlantnet> = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun identify(partMap: MutableMap<String, RequestBody>, images: MultipartBody.Part) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.identify(partMap, images)
                _errorResponse.postValue(response)
                _isLoading.value = false
                Log.d(TAG, "onSuccess: ${response.message}")
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ImageResponsePlantnet::class.java)
                val errorMessage = errorBody.message
                _errorResponse.postValue(errorBody)
                _isLoading.postValue(false)
                Log.d(TAG, "onError: $errorMessage")
            } catch (e: Exception) {
                Log.d(TAG, "onError: ${e.message}")
                _isLoading.value = false
                _errorMessage.value = "Something went wrong!"
            }
        }
    }

    fun insert(plant: PlantEntity) {
        viewModelScope.launch {
            repository.insert(plant)
        }
    }

    companion object {
        private const val TAG = "ScanViewModel"
    }
}