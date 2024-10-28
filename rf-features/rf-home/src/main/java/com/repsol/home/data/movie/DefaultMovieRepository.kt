package com.repsol.home.data.movie

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.home.data.movie.remote.data_source.MovieRemoteDS
import com.repsol.home.data.movie.remote.dto.response.MoviePageResponse
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(
    private val remoteDS: MovieRemoteDS
) {

    suspend fun searchMovies(query: String): DataOutput<MoviePageResponse> {
        return remoteDS.searchMovies(query)
    }
}