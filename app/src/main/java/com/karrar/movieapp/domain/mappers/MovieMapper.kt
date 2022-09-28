package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.utilities.Constants

class MovieMapper : Mapper<MovieDto, Movie> {
    override fun map(input: MovieDto): Movie {
        return Movie(
            input.id ?: 0,
            Constants.IMAGE_BASE_PATH + input.posterPath
        )
    }
}