package com.example.usecase.mappers.savedList

import com.example.models.models.Media
import com.example.usecase.mappers.Mapper
import com.example.usecase.utilites.Constants
import com.thechance.repository.BuildConfig
import com.thechance.repository.remote.response.DailyTrendingDto
import javax.inject.Inject

class ItemListMapper @Inject constructor() : Mapper<DailyTrendingDto, Media> {
    override fun map(input: DailyTrendingDto): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            mediaType = input.mediaType ?: Constants.MOVIE,
            input.originalLanguage ?: input.originalTitle ?: "",
            input.releaseDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0F
        )
    }
}
