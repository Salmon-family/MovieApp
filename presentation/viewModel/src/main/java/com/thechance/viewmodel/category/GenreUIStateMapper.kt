package com.thechance.viewmodel.category

import com.devfalah.models.Genre
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.category.uiState.GenreUIState
import javax.inject.Inject

class GenreUIStateMapper @Inject constructor() :
    Mapper<Genre, GenreUIState> {
    override fun map(input: Genre): GenreUIState {
        return GenreUIState(
            genreID = input.genreID,
            genreName = input.genreName
        )
    }
}