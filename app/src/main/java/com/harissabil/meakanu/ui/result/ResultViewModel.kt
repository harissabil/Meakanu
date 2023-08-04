package com.harissabil.meakanu.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harissabil.meakanu.data.PlantRepository
import com.harissabil.meakanu.data.local.entity.PlantEntity
import kotlinx.coroutines.launch

class ResultViewModel(private val repository: PlantRepository) : ViewModel() {

    fun update(plant: PlantEntity) {
        viewModelScope.launch {
            repository.update(plant)
        }
    }

    suspend fun getLastRow() = repository.getLastRow()

}