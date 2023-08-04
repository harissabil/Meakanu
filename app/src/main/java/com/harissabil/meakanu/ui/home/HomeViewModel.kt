package com.harissabil.meakanu.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harissabil.meakanu.data.PlantRepository
import com.harissabil.meakanu.data.local.entity.PlantEntity
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PlantRepository) : ViewModel() {

    private val _count = MutableLiveData<Int>().apply {
        value = 0
    }
    val count: LiveData<Int> = _count

    fun clickBanner() {
        _count.value = _count.value?.plus(1)
    }

    fun resetCount() {
        _count.value = 0
    }

    fun delete(plant: PlantEntity) {
        viewModelScope.launch {
            repository.delete(plant)
        }
    }

    fun getLatest() = repository.getLatest()
}
