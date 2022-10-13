package com.karrar.movieapp.domain.models

data class Episode(
    val episodeId: Int,
    val imageUrl: String,
    val episodeName: String,
    val episodeDuration: String,
    val episodeDate: String,
    val episodeRate: Double,
    val episodeTotalReviews: String,
    val episodeDescription: String
)