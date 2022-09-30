package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.Movie
import javax.inject.Inject


class MovieMapper @Inject constructor():Mapper<MovieDto, Movie> {
    override fun map(input: MovieDto): Movie {
        return Movie(
            input.id,
            input.posterPath
        )
    }
}