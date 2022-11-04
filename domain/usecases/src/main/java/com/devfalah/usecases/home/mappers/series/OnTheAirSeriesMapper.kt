package com.devfalah.usecases.home.mappers.series

import com.thechance.local.database.entity.series.OnTheAirSeriesEntity
import com.devfalah.types.MediaType
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Media
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class OnTheAirSeriesMapper @Inject constructor() :
    Mapper<OnTheAirSeriesEntity, Media> {
    override fun map(input: OnTheAirSeriesEntity): Media {
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