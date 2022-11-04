package com.thechance.movie.ui.home

import com.thechance.movie.ui.home.homeUiState.PopularUiState
import javax.inject.Inject


class PopularUiMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.PopularMovie, PopularUiState> {
    override fun map(input: com.devfalah.models.PopularMovie): PopularUiState {
        return PopularUiState(
            input.movieID,
            input.title,
            input.imageUrl,
            input.movieRate,
            input.genre
        )
    }
}