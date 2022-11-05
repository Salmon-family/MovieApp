package com.thechance.viewmodel.category.uiState

import com.thechance.ui.utilities.Constants

data class GenreUIState(
    val genreID: Int = Constants.FIRST_CATEGORY_ID,
    val genreName: String = ""
)
