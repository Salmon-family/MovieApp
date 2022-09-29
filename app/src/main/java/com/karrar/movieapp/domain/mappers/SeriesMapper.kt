package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.domain.models.Series
import com.karrar.movieapp.utilities.Constants

class SeriesMapper : Mapper<MovieDto, Series> {
    override fun map(input: MovieDto): Series {
        return Series(
            input.id ?: 0,
            Constants.IMAGE_BASE_PATH + input.posterPath
        )
    }
}