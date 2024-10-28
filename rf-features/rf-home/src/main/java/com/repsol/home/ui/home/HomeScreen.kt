package com.repsol.home.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.repsol.core_ui.stateful.ScreenPreview
import com.repsol.core_ui.stateful.Stateful
import com.repsol.home.data.movie.remote.dto.response.MovieResponse
import com.repsol.home.ui.home.interactor.HomeUiState as UiState

@Composable
fun HomeScreen() = Stateful<HomeViewModel> {
    val uiState by uiState()
    Scaffold { scaffoldPaddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPaddingValues)
        ) {
            items(uiState.movies) { movie ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.height(200.dp)
                    ) {
                        AsyncImage(
                            model = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2${movie.backdropPath}",
                            contentDescription = "Movie poster",
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.4f)
                                .background(color = Color.Gray)
                        )
                        Column(
                            modifier = Modifier
                                .weight(0.6f)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = movie.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = movie.overview,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultHomeScreenPreview() {
    ScreenPreview(
        uiState = UiState(
            movies = listOf(
                MovieResponse(
                    title = "Batman",
                    overview = "The Dark Knight",
                    posterPath = "/path/to/image",
                    backdropPath = "/path/to/image",
                    releaseDate = "2021-10-10",
                    voteAverage = 8.0,
                    voteCount = 1000,
                    id = 1,
                    originalLanguage = "en",
                    originalTitle = "Batman",
                    popularity = 100.0,
                    video = false,
                    adult = false,
                    genreIds = listOf(1, 2, 3)
                )
            )
        )
    ) {
        HomeScreen()
    }
}