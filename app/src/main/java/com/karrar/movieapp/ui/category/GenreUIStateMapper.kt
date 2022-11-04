package com.karrar.movieapp.ui.category

import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.ui.category.uiState.GenreUIState
import javax.inject.Inject

class GenreUIStateMapper @Inject constructor() : Mapper<Genre, GenreUIState> {
    override fun map(input: Genre): GenreUIState {
        return GenreUIState(
            genreID = input.genreID,
            genreName = input.genreName
        )
    }
}