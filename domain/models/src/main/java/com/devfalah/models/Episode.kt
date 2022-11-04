package com.devfalah.models

data class Episode(
    val episodeId: Int,
    val imageUrl: String,
    val episodeName: String,
    val episodeDuration: Int,
    val episodeDate: String,
    val episodeRate: Double,
    val episodeTotalReviews: String,
    val episodeDescription: String,
    val episodeNumber: Int,
)