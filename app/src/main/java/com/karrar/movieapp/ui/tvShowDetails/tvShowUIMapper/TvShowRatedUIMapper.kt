package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Rated
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.RatedUIState
import javax.inject.Inject

class TvShowRatedUIMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Rated, RatedUIState> {
    override fun map(input: com.devfalah.models.Rated): RatedUIState {
        return RatedUIState(
            id = input.id,
            rating = input.rating,
        )
    }
}
