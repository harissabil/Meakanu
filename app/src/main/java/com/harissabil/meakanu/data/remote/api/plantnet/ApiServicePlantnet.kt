package com.harissabil.meakanu.data.remote.api.plantnet

import com.harissabil.meakanu.BuildConfig
import com.harissabil.meakanu.data.remote.response.plantnet.ImageResponsePlantnet
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServicePlantnet {
    @Multipart
    @POST("identify/{project}")
    suspend fun identify(@Path("project") project: String = "all",
                         @Query("api-key") token: String = BuildConfig.API_KEY_PLANTNET,
                         @PartMap partMap: MutableMap<String, RequestBody>,
                         @Part images: MultipartBody.Part
    ): ImageResponsePlantnet
}