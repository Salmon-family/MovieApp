package com.thechance.movie.ui.category

import com.thechance.movie.ui.category.uiState.GenreUIState
import javax.inject.Inject

class GenreUIStateMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Genre, GenreUIState> {
    override fun map(input: com.devfalah.models.Genre): GenreUIState {
        return GenreUIState(
            genreID = input.genreID,
            genreName = input.genreName
        )
    }
}