package com.repsol.home.data.movie.remote

import com.repsol.home.data.movie.remote.dto.response.MoviePageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String
    ): Response<MoviePageResponse>
}