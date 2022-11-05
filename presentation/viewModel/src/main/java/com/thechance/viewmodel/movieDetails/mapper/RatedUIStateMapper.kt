package com.thechance.viewmodel.movieDetails.mapper

import com.devfalah.models.Rated
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.movieDetails.movieDetailsUIState.RatedUIState
import javax.inject.Inject

class RatedUIStateMapper @Inject constructor() :
    Mapper<Rated, RatedUIState> {
    override fun map(input: Rated): RatedUIState {
        return RatedUIState(
            id = input.id,
            rating = input.rating,
        )
    }
}