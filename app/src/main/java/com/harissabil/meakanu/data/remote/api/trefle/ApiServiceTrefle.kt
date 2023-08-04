package com.harissabil.meakanu.data.remote.api.trefle

import com.harissabil.meakanu.BuildConfig
import com.harissabil.meakanu.data.remote.response.trefle.IdResponseTrefle
import com.harissabil.meakanu.data.remote.response.trefle.SearchResponseTrefle
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceTrefle {
    @GET("plants/search")
    suspend fun byQ(@Query("q") searchQ: String?,
                    @Query("page") page: Int = 1,
                    @Query("token") token: String = BuildConfig.API_KEY_TREFLE
    ): SearchResponseTrefle

    @GET("plants/{plantId}")
    suspend fun byId(@Path("plantId") plantId: Int,
                     @Query("token") token: String = BuildConfig.API_KEY_TREFLE
    ): IdResponseTrefle
}