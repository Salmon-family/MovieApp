package com.devfalah.usecases.home.mappers.movie

import com.thechance.remote.response.MovieDto
import com.devfalah.types.MediaType
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Media
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieDto, Media> {
    override fun map(input: MovieDto): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            MediaType.MOVIE.value,
            input.originalTitle ?: "",
            input.releaseDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0f
        )
    }
}