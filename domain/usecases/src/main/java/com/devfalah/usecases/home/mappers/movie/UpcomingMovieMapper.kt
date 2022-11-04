package com.devfalah.usecases.home.mappers.movie

import com.thechance.local.database.entity.movie.UpcomingMovieEntity
import com.devfalah.types.MediaType
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Media
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class UpcomingMovieMapper @Inject constructor() :
    Mapper<UpcomingMovieEntity, Media> {
    override fun map(input: UpcomingMovieEntity): Media {
        return Media(
            mediaID = input.id,
            mediaName = input.name,
            mediaImage = BuildConfig.IMAGE_BASE_PATH + input.imageUrl,
            mediaRate = 0f,
            mediaDate = "",
            mediaType = MediaType.MOVIE.value,
        )
    }
}