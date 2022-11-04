package com.thechance.movie.ui.home.homeUiState

data class PopularUiState(
    val movieID: Int,
    val title: String,
    val imageUrl: String,
    val movieRate:Double,
    val genre: List<String>
)