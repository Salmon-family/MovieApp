package com.karrar.movieapp.domain.usecases.home.mappers.movie

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.MovieDto
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieDto, Media> {
    override fun map(input: com.thechance.remote.response.MovieDto): Media {
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