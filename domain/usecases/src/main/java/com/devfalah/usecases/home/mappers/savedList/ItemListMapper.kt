package com.devfalah.usecases.home.mappers.savedList

import com.thechance.remote.response.DailyTrendingDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Media
import com.devfalah.types.MediaType
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class ItemListMapper @Inject constructor() : Mapper<DailyTrendingDto, Media> {
    override fun map(input: DailyTrendingDto): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            mediaType = input.mediaType ?: MediaType.MOVIE.value,
            input.originalLanguage ?: input.originalTitle ?: "",
            input.releaseDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0F,
        )
    }
}