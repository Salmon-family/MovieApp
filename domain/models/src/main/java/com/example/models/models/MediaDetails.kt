package com.example.models.models

import com.example.models.utilities.MediaType

abstract class MediaDetails(
    val id: Int,
    val image: String,
    val name: String,
    val releaseDate: String,
    val genres: String,
    val specialNumber: Int,
    val review: Int,
    val voteAverage: String,
    val overview: String,
    val seasons: List<Season>,
    val mediaType: MediaType
)