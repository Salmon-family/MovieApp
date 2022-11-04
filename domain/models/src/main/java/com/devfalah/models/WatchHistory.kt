package com.devfalah.models

data class WatchHistory(
    val id: Int,
    val posterPath: String,
    val movieTitle: String,
    val voteAverage: String,
    val releaseDate: String,
    val movieDuration: Int,
    val mediaType:String
)
