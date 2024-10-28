package com.repsol.home.data.movie.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("poster_path")
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("original_language")
    val originalLanguage: String,
    val title: String,
    @SerialName("backdrop_path")
    val backdropPath: String,
    val popularity: Double,
    @SerialName("vote_count")
    val voteCount: Int,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"
    val backdropUrl: String
        get() = "https://image.tmdb.org/t/p/w500$backdropPath"
}