package com.harissabil.meakanu.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.harissabil.meakanu.data.local.entity.PlantEntity
import com.harissabil.meakanu.data.local.room.PlantDao
import com.harissabil.meakanu.data.remote.api.news.ApiServiceNews
import com.harissabil.meakanu.data.remote.api.news.NewsPagingSource
import com.harissabil.meakanu.data.remote.api.plantnet.ApiServicePlantnet
import com.harissabil.meakanu.data.remote.api.trefle.ApiServiceTrefle
import com.harissabil.meakanu.data.remote.response.news.ArticlesItem
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PlantRepository private constructor(
    private val apiServiceTrefle: ApiServiceTrefle,
    private val apiServicePlantnet: ApiServicePlantnet,
    private val apiServiceNews: ApiServiceNews,
    private val plantDao: PlantDao
) {
    // Plantnet
    suspend fun identify(partMap: MutableMap<String, RequestBody>, images: MultipartBody.Part) =
        apiServicePlantnet.identify(partMap = partMap, images = images)

    // Trefle
    suspend fun byQ(query: String) = apiServiceTrefle.byQ(query)

    suspend fun byId(plantId: Int) = apiServiceTrefle.byId(plantId)

    // News

    fun getNews(): LiveData<PagingData<ArticlesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                initialLoadSize = 5
            ),
            pagingSourceFactory = {
                NewsPagingSource(apiServiceNews)
            }
        ).liveData
    }

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
            apiServiceNews: ApiServiceNews,
            plantDao: PlantDao
        ): PlantRepository =
            instance ?: synchronized(this) {
                instance ?: PlantRepository(
                    apiServiceTrefle,
                    apiServicePlantnet,
                    apiServiceNews,
                    plantDao
                )
            }.also { instance = it }
    }
}