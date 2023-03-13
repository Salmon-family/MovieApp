package com.karrar.movieapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karrar.movieapp.ui.composable.ActorItem
import com.karrar.movieapp.ui.composable.MediaItem
import com.karrar.movieapp.ui.ui.theme.MovieAppTheme

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
                    ActorItem(
                        modifier = Modifier.padding(8.dp),
                        actorName = "Test test",
                        actorImageUrl = "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg",
                        onClick = { }
                    )

                    MediaItem(
                        mediaImageUrl = "https://image.tmdb.org/t/p/w500/8V1XPEDLtJoxOWlE6gYzn2sQaMp.jpg",
                        onClick = {}
                    )
                }
            }
        }
    }
}

