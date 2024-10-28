package com.repsol.core_data.common.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import kotlin.reflect.KClass

class ApiCreator @Inject constructor() {

    fun <T: Any> create(
        service: KClass<T>
    ): T {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2OGNmZmY1MzBkMTIyZDc0Mjk5YTliNzcwMjlhNWZkZCIsIm5iZiI6MTcyNjQyNDI1OC4wNjY2MzMsInN1YiI6IjY2ZTcxZmYzMzc2OGE3M2Y4ZDkxNzk1ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.P_-iAb0cIUTEjaVnWpKNDch-K6qvEgHLfVmklmgKxNM")
                    .build()
                it.proceed(request)
            }
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
        return retrofit.create(service.java)
    }

    inline fun <reified T: Any> create(): T = create(T::class)
}