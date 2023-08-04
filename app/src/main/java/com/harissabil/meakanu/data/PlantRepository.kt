package com.harissabil.meakanu.data

import com.harissabil.meakanu.data.local.entity.PlantEntity
import com.harissabil.meakanu.data.local.room.PlantDao
import com.harissabil.meakanu.data.remote.api.plantnet.ApiServicePlantnet
import com.harissabil.meakanu.data.remote.api.trefle.ApiServiceTrefle
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PlantRepository private constructor(
    private val apiServiceTrefle: ApiServiceTrefle,
    private val apiServicePlantnet: ApiServicePlantnet,
    private val plantDao: PlantDao
) {
    // Plantnet
    suspend fun identify(partMap: MutableMap<String, RequestBody>, images: MultipartBody.Part) =
        apiServicePlantnet.identify(partMap = partMap, images = images)

    // Trefle
    suspend fun byQ(query: String) = apiServiceTrefle.byQ(query)

    suspend fun byId(plantId: Int) = apiServiceTrefle.byId(plantId)

    // Database
    suspend fun insert(plant: PlantEntity) = plantDao.insert(plant)

    suspend fun update(plant: PlantEntity) = plantDao.update(plant)

    suspend fun delete(plant: PlantEntity) = plantDao.delete(plant)

    fun getLatest() = plantDao.getLatest()

    suspend fun getLastRow() = plantDao.getLastRow()

    companion object {
        @Volatile
        private var instance: PlantRepository? = null
        fun getInstance(
            apiServiceTrefle: ApiServiceTrefle,
            apiServicePlantnet: ApiServicePlantnet,
            plantDao: PlantDao
        ): PlantRepository =
            instance ?: synchronized(this) {
                instance ?: PlantRepository(apiServiceTrefle, apiServicePlantnet, plantDao)
            }.also { instance = it }
    }
}