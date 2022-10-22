package com.karrar.movieapp.domain.mappers.savedList

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.DailyTrendingDto
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class ItemListMapper @Inject constructor() : Mapper<DailyTrendingDto, Media> {
    override fun map(input: DailyTrendingDto): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            mediaType = input.mediaType ?: Constants.MOVIE,
            input.originalLanguage ?: input.originalTitle ?: "",
            input.releaseDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0F,
        )
    }
}