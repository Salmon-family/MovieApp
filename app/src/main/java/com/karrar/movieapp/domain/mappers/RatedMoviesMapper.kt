package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.RatedMoviesDto
import com.karrar.movieapp.domain.models.RatedMovie
import com.karrar.movieapp.utilities.Constants.IMAGE_BASE_PATH
import javax.inject.Inject

class RatedMoviesMapper @Inject constructor() : Mapper<RatedMoviesDto, RatedMovie> {
    override fun map(input: RatedMoviesDto): RatedMovie {
        return RatedMovie(
            id = input.id,
            title = input.title,
            posterPath = IMAGE_BASE_PATH + input.posterPath,
            rating = input.rating,
            releaseDate = input.releaseDate,
        )
    }
}