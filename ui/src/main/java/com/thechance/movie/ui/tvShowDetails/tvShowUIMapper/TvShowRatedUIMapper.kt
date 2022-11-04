package com.thechance.movie.ui.tvShowDetails.tvShowUIMapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Rated
import com.thechance.movie.ui.tvShowDetails.tvShowUIState.RatedUIState
import javax.inject.Inject

class TvShowRatedUIMapper @Inject constructor() :
    Mapper<Rated, RatedUIState> {
    override fun map(input: Rated): RatedUIState {
        return RatedUIState(
            id = input.id,
            rating = input.rating,
        )
    }
}
