package com.thechance.movie.ui.category.uiState

import com.thechance.movie.utilities.Constants

data class GenreUIState(
    val genreID: Int = Constants.FIRST_CATEGORY_ID,
    val genreName: String = ""
)
