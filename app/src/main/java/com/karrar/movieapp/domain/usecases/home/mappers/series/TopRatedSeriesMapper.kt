package com.karrar.movieapp.domain.usecases.home.mappers.series

import com.karrar.movieapp.BuildConfig
import com.thechance.local.database.entity.series.TopRatedSeriesEntity
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject

class TopRatedSeriesMapper @Inject constructor() : Mapper<TopRatedSeriesEntity, Media> {
    override fun map(input: TopRatedSeriesEntity): Media {
        return Media(
            mediaID = input.id,
            mediaName = input.name,
            mediaImage = BuildConfig.IMAGE_BASE_PATH + input.imageUrl,
            mediaRate = 0f,
            mediaDate = "",
            mediaType = MediaType.TV_SHOW.value,
        )
    }
}