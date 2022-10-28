package com.karrar.movieapp.ui.movieDetails.mapper

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.RatedUIState
import javax.inject.Inject

class RatedUIStateMapper @Inject constructor() : Mapper<Rated, RatedUIState> {
    override fun map(input: Rated): RatedUIState {
        return RatedUIState(
            id = input.id,
            rating = input.rating,
        )
    }
}