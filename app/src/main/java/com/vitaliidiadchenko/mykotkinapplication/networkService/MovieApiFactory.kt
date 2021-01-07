package com.vitaliidiadchenko.mykotkinapplication.networkService

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.vitaliidiadchenko.mykotkinapplication.BuildConfig
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

object MovieApiFactory {
    private val json = Json { ignoreUnknownKeys = true }

    @ExperimentalSerializationApi
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Suppress("unused")
    val movieApiService: MovieApiService = retrofit.create()
}