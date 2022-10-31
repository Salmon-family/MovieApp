package com.karrar.movieapp.domain.mappers.movie

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.utilities.Constants
import com.thechance.repository.remote.response.RatedMoviesDto
import javax.inject.Inject

class RatedMoviesMapper @Inject constructor() : Mapper<RatedMoviesDto, Rated> {
    override fun map(input: RatedMoviesDto): Rated {
        return Rated(
            id = input.id ?: 0,
            title = input.title ?: "",
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rating = input.rating ?: 0F,
            releaseDate = input.releaseDate ?: "",
            mediaType = Constants.MOVIE
        )
    }
}