package com.repsol.home.data.movie.remote.data_source

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.home.data.movie.remote.dto.response.MoviePageResponse

interface MovieRemoteDS {

    suspend fun searchMovies(query: String): DataOutput<MoviePageResponse>
}