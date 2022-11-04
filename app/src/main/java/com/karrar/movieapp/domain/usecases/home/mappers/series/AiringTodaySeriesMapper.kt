package com.karrar.movieapp.domain.usecases.home.mappers.series

import com.karrar.movieapp.BuildConfig
import com.thechance.local.database.entity.series.AiringTodaySeriesEntity
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject

class AiringTodaySeriesMapper @Inject constructor() : Mapper<AiringTodaySeriesEntity, Media> {
    override fun map(input: com.thechance.local.database.entity.series.AiringTodaySeriesEntity): Media {
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