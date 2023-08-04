package com.harissabil.meakanu.ui.plant

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.harissabil.meakanu.data.PlantRepository
import com.harissabil.meakanu.data.remote.response.trefle.SearchResponseTrefle
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PlantWithoutBottomNavViewModel(private val repository: PlantRepository) : ViewModel() {
    val searchText = MutableLiveData<String>()

    private val _searchResponse = MutableLiveData<SearchResponseTrefle>()
    val searchResponse: LiveData<SearchResponseTrefle> = _searchResponse

    private val _errorResponse = MutableLiveData<SearchResponseTrefle>()
    val errorResponse: LiveData<SearchResponseTrefle> = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    fun byQuery(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.byQ(query)
                _searchResponse.postValue(response)
                _isLoading.value = false
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, SearchResponseTrefle::class.java)
                val errorMessage = errorBody.message
                _isLoading.postValue(false)
                _errorResponse.postValue(errorBody)
                Log.d(TAG, "onError: $errorMessage")
            } catch (e: Exception) {
                _isLoading.value = false
                _isError.value = true
            }
        }
    }

    companion object {
        private const val TAG = "PlantWithoutBottomNavViewModel"
    }
}