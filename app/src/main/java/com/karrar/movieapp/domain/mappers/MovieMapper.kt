package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.MediaInfo
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieDto, MediaInfo> {
    override fun map(input: MovieDto): MediaInfo {
        return MediaInfo(
            type = null,
            id = input.id,
            name = input.originalTitle,
            releaseDate = input.releaseDate?.substringBefore('-'),
            rate = input.voteAverage?.toFloat(),
            imagePath = BuildConfig.IMAGE_BASE_PATH + input.backdropPath,
            profileImage = null
        )
    }
}