package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.movie.MovieDto
import com.karrar.movieapp.domain.models.MediaInfo
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieDto, MediaInfo> {
    override fun map(input: MovieDto): MediaInfo {
        return MediaInfo(
            type = Constants.MOVIE,
            id = input.id ?: 0,
            name = input.originalTitle ?: "",
            releaseDate = input.releaseDate?.substringBefore('-') ?: "",
            rate = input.voteAverage?.toFloat() ?: 0F,
            imagePath = Constants.IMAGE_BASE_PATH + input.backdropPath,
        )
    }
}