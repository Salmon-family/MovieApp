package com.karrar.movieapp.domain.models

data class Media(
    val mediaID: Int,
    val mediaImage: String,
    val mediaType:String,
    val mediaName: String,
    val mediaDate: String,
    val mediaRate: Float,
)
