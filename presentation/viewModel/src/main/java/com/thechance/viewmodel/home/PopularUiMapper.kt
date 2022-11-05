package com.thechance.viewmodel.home

import com.devfalah.models.PopularMovie
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.category.com.thechance.viewmodel.home.homeUiState.PopularUiState
import javax.inject.Inject


class PopularUiMapper @Inject constructor() :
    Mapper<PopularMovie, PopularUiState> {
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