package com.repsol.auth.data.remote.api

import com.repsol.auth.data.remote.dto.response.UserInformationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInformationApi {

    @GET("v1/Person/getUserInformation")
    suspend fun getUserInformation(
        @Query("email") email: String,
    ): Response<UserInformationResponse>
}