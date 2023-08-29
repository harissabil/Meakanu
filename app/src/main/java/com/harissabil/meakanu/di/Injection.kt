package com.harissabil.meakanu.di

import android.content.Context
import com.harissabil.meakanu.data.PlantRepository
import com.harissabil.meakanu.data.local.room.PlantDatabase
import com.harissabil.meakanu.data.remote.api.news.ApiConfigNews
import com.harissabil.meakanu.data.remote.api.plantnet.ApiConfigPlantnet
import com.harissabil.meakanu.data.remote.api.trefle.ApiConfigTrefle

object Injection {
    fun providePlantRepository(context: Context): PlantRepository {
        val apiServiceTrefle = ApiConfigTrefle.getApiService(context)
        val apiServicePlantnet = ApiConfigPlantnet.getApiService()
        val apiServiceNews = ApiConfigNews.getApiService()
        val plantDao = PlantDatabase.getDatabase(context)
        val dao = plantDao.plantDao()
        return PlantRepository.getInstance(
            apiServiceTrefle,
            apiServicePlantnet,
            apiServiceNews,
            dao
        )
    }
}