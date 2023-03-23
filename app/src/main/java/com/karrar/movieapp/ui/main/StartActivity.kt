package com.karrar.movieapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.karrar.movieapp.ui.category.uiState.MediaUIState
import com.karrar.movieapp.ui.composable.AiringToday
import com.karrar.movieapp.ui.theme.MovieAppTheme
import com.karrar.movieapp.R

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {


                        AiringToday(
                            medias = listOf(
                                MediaUIState(mediaImage = "https://image.tmdb.org/t/p/w500/gOnmaxHo0412UVr1QM5Nekv1xPi.jpg"),
                                MediaUIState(mediaImage = "https://image.tmdb.org/t/p/w500/gOnmaxHo0412UVr1QM5Nekv1xPi.jpg"),
                                MediaUIState(mediaImage = "https://image.tmdb.org/t/p/w500/gOnmaxHo0412UVr1QM5Nekv1xPi.jpg"),
                                MediaUIState(mediaImage = "https://image.tmdb.org/t/p/w500/gOnmaxHo0412UVr1QM5Nekv1xPi.jpg"),
                                MediaUIState(mediaImage = "https://image.tmdb.org/t/p/w500/gOnmaxHo0412UVr1QM5Nekv1xPi.jpg"),
                                MediaUIState(mediaImage = "https://image.tmdb.org/t/p/w500/gOnmaxHo0412UVr1QM5Nekv1xPi.jpg"),
                                MediaUIState(mediaImage = "https://image.tmdb.org/t/p/w500/gOnmaxHo0412UVr1QM5Nekv1xPi.jpg"),
                                MediaUIState(mediaImage = "https://image.tmdb.org/t/p/w500/gOnmaxHo0412UVr1QM5Nekv1xPi.jpg"),
                            ),
                            onClickMedia = {},
                            cardTitleRes = R.string.title_airing_today
                        )
                    }
                }
            }
        }
    }
}

