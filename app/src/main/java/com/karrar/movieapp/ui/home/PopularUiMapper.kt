package com.karrar.movieapp.ui.home

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.PopularMovie
import com.karrar.movieapp.ui.home.homeUiState.PopularUiState
import javax.inject.Inject


class PopularUiMapper @Inject constructor() : Mapper<PopularMovie, PopularUiState> {
    override fun map(input: PopularMovie): PopularUiState {
        return PopularUiState(
            input.movieID,
            input.title,
            input.imageUrl,
            input.movieRate,
            input.genre
        )
    }
}