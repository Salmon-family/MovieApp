package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.PopularMovie
import com.karrar.movieapp.utilities.Constants.IMAGE_BASE_PATH

class PopularMovieMapper : Mapper<MovieDto, PopularMovie> {
    override fun map(input: MovieDto): PopularMovie {
        return PopularMovie(
            movieID = input.id ?: 0,
            title = input.title ?: "",
            movieRate = input.voteAverage ?: 0.0,
            imageUrl = (IMAGE_BASE_PATH + input.backdropPath)
        )
    }
}