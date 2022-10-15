package com.karrar.movieapp.domain.mappers.movie

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieDto, Media> {
    override fun map(input: MovieDto): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            Constants.MOVIE,
            input.originalTitle ?: "",
            input.releaseDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0f
        )
    }
}