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
            .build()
        val json = Json { ignoreUnknownKeys = true }
        val retrofit = Retrofit.Builder()
            .baseUrl("https://preproduccion.everilion.com/IlionServices4/RepsolPeru_API/Flotas/api/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
        return retrofit.create(service.java)
    }

    inline fun <reified T: Any> create(): T = create(T::class)
}