package com.bursur.spiragps.utils

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

object ApiClient {
    private val apiClient: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://bursur.github.io/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    val apiService: ApiService by lazy {
        apiClient.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET
    fun getJson(@Url url: String): Call<String>
}