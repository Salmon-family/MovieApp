package com.karrar.movieapp.ui.tvShowDetails.tvShowUIState

data class EpisodeUIState(
    val episodeId: Int = 0,
    val imageUrl: String = "",
    val episodeName: String = "",
    val episodeDuration: Int = 0,
    val episodeDate: String = "",
    val episodeRate: Double = 0.0,
    val episodeTotalReviews: String = "",
    val episodeDescription: String = "",
    val episodeNumber: Int = 0
)
