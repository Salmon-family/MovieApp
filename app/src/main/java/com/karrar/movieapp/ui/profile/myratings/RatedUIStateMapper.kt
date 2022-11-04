package com.karrar.movieapp.ui.profile.myratings

import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Rated
import javax.inject.Inject

class RatedUIStateMapper @Inject constructor() : Mapper<Rated, RatedUIState> {
    override fun map(input: Rated): RatedUIState {
        return RatedUIState(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            rating = input.rating,
            mediaType = input.mediaType,
            releaseDate = input.releaseDate
        )
    }
}