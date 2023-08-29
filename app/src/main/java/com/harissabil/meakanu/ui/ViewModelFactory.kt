package com.harissabil.meakanu.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harissabil.meakanu.data.PlantRepository
import com.harissabil.meakanu.di.Injection
import com.harissabil.meakanu.ui.agri.AgriViewModel
import com.harissabil.meakanu.ui.detail.PlantDetailViewModel
import com.harissabil.meakanu.ui.home.HistoryDetailViewModel
import com.harissabil.meakanu.ui.home.HomeViewModel
import com.harissabil.meakanu.ui.plant.PlantViewModel
import com.harissabil.meakanu.ui.plant.PlantWithoutBottomNavViewModel
import com.harissabil.meakanu.ui.result.ResultViewModel
import com.harissabil.meakanu.ui.scan.ScanViewModel

class ViewModelFactory(private val repository: PlantRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PlantViewModel::class.java)) {
            PlantViewModel(this.repository) as T
        } else if (modelClass.isAssignableFrom(PlantDetailViewModel::class.java)) {
            PlantDetailViewModel(this.repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(this.repository) as T
        } else if (modelClass.isAssignableFrom(ScanViewModel::class.java)) {
            ScanViewModel(this.repository) as T
        } else if (modelClass.isAssignableFrom(PlantWithoutBottomNavViewModel::class.java)) {
            PlantWithoutBottomNavViewModel(this.repository) as T
        } else if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            ResultViewModel(this.repository) as T
        } else if (modelClass.isAssignableFrom(HistoryDetailViewModel::class.java)) {
            HistoryDetailViewModel(this.repository) as T
        } else if (modelClass.isAssignableFrom(AgriViewModel::class.java)) {
            AgriViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.providePlantRepository(context))
            }.also { instance = it }
    }
}