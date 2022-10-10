package com.karrar.movieapp.domain.models

abstract class MediaDetails(
    val movieID:Int,
    val movieImage:String,
    val movieName: String,
    val releaseDate: String,
    val genres: String,
    val duration: Int,
    val review: Int,
    val voteAverage: String,
    val overView: String,
    val seasons: List<Season>
)