package com.harissabil.meakanu.data.remote.api.trefle

import android.content.Context
import com.harissabil.meakanu.BuildConfig
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class ApiConfigTrefle {
    companion object {
        fun getApiService(context: Context): ApiServiceTrefle {

            val cacheSize = 20 * 1024 * 1024 // 20 MB
            val httpCacheDirectory = File(context.externalCacheDir, "http-cache")
            val cache = Cache(httpCacheDirectory, cacheSize.toLong())

            val networkCacheInterceptor = Interceptor { chain ->
                val response = chain.proceed(chain.request())

                var cacheControl = CacheControl.Builder()
                    .maxAge(7, TimeUnit.DAYS)
                    .build()

                response.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build()
            }

            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val client = OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(networkCacheInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_TREFLE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiServiceTrefle::class.java)
        }
    }
}