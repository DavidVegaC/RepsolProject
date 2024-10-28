package com.repsol.home.data.movie.remote.data_source

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.safeApiCall
import com.repsol.home.data.movie.remote.MovieService
import com.repsol.home.data.movie.remote.dto.response.MoviePageResponse
import javax.inject.Inject

class DefaultMovieRemoteDS @Inject constructor(
    private val service: MovieService
): MovieRemoteDS {

    override suspend fun searchMovies(query: String): DataOutput<MoviePageResponse> {
        return safeApiCall { service.searchMovies(query) }
    }
}