package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.RatedUIState
import javax.inject.Inject

class TvShowRatedUIMapper @Inject constructor() : Mapper<Rated, RatedUIState> {
    override fun map(input: Rated): RatedUIState {
        return RatedUIState(
            id = input.id,
            rating = input.rating,
        )
    }
}
