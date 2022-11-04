package com.devfalah.usecases.home.mappers.movie

import com.devfalah.models.Rated
import com.thechance.remote.response.RatedMoviesDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.types.MediaType
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class RatedMoviesMapper @Inject constructor() : Mapper<RatedMoviesDto, Rated> {
    override fun map(input: RatedMoviesDto): Rated {
        return Rated(
            id = input.id ?: 0,
            title = input.title ?: "",
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.backdropPath,
            rating = input.rating ?: 0F,
            releaseDate = input.releaseDate ?: "",
            mediaType = MediaType.MOVIE.value
        )
    }
}