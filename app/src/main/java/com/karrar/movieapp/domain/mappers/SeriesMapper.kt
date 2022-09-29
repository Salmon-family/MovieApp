package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.Series
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class SeriesMapper @Inject constructor() : Mapper<MovieDto, Series> {
    override fun map(input: MovieDto): Series {
        return Series(
            input.id ?: 0,
            Constants.IMAGE_BASE_PATH + input.posterPath
        )
    }
}