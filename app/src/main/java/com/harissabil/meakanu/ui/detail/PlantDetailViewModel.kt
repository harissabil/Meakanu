package com.harissabil.meakanu.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.harissabil.meakanu.data.PlantRepository
import com.harissabil.meakanu.data.remote.response.trefle.IdResponseTrefle
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PlantDetailViewModel(private val repository: PlantRepository) : ViewModel() {
    private val _idResponse = MutableLiveData<IdResponseTrefle>()
    val idResponse: LiveData<IdResponseTrefle> = _idResponse

    private val _errorResponse = MutableLiveData<IdResponseTrefle>()
    val errorResponse: LiveData<IdResponseTrefle> = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    fun byId(plantId: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.byId(plantId)
                _idResponse.postValue(response)
                _isLoading.value = false
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, IdResponseTrefle::class.java)
                val errorMessage = errorBody.message
                _isLoading.postValue(false)
                _errorResponse.postValue(errorBody)
                Log.d(TAG, "onError: $errorMessage")
            } catch (e: Exception) {
                Log.e(TAG, "onError: ${e.message}")
                _isLoading.value = false
                _isError.value = true
            }
        }
    }

    companion object {
        const val TAG = "PlantDetailViewModel"
    }
}