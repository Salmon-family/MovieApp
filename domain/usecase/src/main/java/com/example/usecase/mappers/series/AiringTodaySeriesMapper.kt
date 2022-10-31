package com.example.usecase.mappers.series

import com.example.models.models.Media
import com.example.models.utilities.MediaType
import com.example.usecase.mappers.Mapper
import com.thechance.repository.BuildConfig
import com.thechance.repository.local.database.entity.series.AiringTodaySeriesEntity
import javax.inject.Inject

class AiringTodaySeriesMapper @Inject constructor() : Mapper<AiringTodaySeriesEntity, Media> {
    override fun map(input: AiringTodaySeriesEntity): Media {
        return Media(
            mediaID = input.id,
            mediaName = input.name,
            mediaImage = BuildConfig.IMAGE_BASE_PATH + input.imageUrl,
            mediaRate = 0f,
            mediaDate = "",
            mediaType = MediaType.TV_SHOW.value
        )
    }
}
