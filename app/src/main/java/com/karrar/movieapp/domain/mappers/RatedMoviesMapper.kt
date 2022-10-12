package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.RatedMoviesDto
import com.karrar.movieapp.domain.models.RatedMovies
import com.karrar.movieapp.utilities.Constants.IMAGE_BASE_PATH
import javax.inject.Inject

class RatedMoviesMapper @Inject constructor() : Mapper<RatedMoviesDto, RatedMovies> {
    override fun map(input: RatedMoviesDto): RatedMovies {
        return RatedMovies(
            id = input.id ?: 0,
            title = input.title ?: "",
            posterPath = IMAGE_BASE_PATH + input.posterPath,
            rating = input.rating ?: 0F,
            releaseDate = input.releaseDate ?: "",
        )
    }
}