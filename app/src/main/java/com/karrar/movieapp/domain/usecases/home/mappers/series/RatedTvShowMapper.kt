package com.karrar.movieapp.domain.usecases.home.mappers.series

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.RatedTvShowDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class RatedTvShowMapper @Inject constructor() : Mapper<RatedTvShowDto, Rated> {
    override fun map(input: com.thechance.remote.response.RatedTvShowDto): Rated {
        return Rated(
            id = input.id ?: 0,
            title = input.title ?: "",
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.backdropPath,
            rating = input.rating ?: 0F,
            releaseDate = "",
            mediaType = Constants.TV_SHOWS
        )
    }
}