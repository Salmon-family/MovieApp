package com.karrar.movieapp.ui.tvShowDetails.tvShowUIState

data class RatedUIState(
    val id: Int = 0,
    val title: String = "",
    val posterPath: String = "",
    val rating: Float = 0F,
    val releaseDate: String = "",
    var mediaType: String = ""
)
