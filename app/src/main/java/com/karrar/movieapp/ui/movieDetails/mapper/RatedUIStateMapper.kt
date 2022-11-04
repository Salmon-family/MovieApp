package com.karrar.movieapp.ui.movieDetails.mapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Rated
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.RatedUIState
import javax.inject.Inject

class RatedUIStateMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Rated, RatedUIState> {
    override fun map(input: com.devfalah.models.Rated): RatedUIState {
        return RatedUIState(
            id = input.id,
            rating = input.rating,
        )
    }
}