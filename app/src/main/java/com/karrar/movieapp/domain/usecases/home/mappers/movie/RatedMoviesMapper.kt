package com.karrar.movieapp.domain.usecases.home.mappers.movie

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.RatedMoviesDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class RatedMoviesMapper @Inject constructor() : Mapper<RatedMoviesDto, Rated> {
    override fun map(input: com.thechance.remote.response.RatedMoviesDto): Rated {
        return Rated(
            id = input.id ?: 0,
            title = input.title ?: "",
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.backdropPath,
            rating = input.rating ?: 0F,
            releaseDate = input.releaseDate ?: "",
            mediaType = Constants.MOVIE
        )
    }
}