package com.devfalah.usecases.home.mappers.movie

import com.thechance.local.database.entity.movie.NowStreamingMovieEntity
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Media
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class NowStreamingMovieMapper @Inject constructor() :
    Mapper<NowStreamingMovieEntity, Media> {
    override fun map(input: NowStreamingMovieEntity): Media {
        return Media(
            mediaID = input.id,
            mediaName = input.name,
            mediaImage = BuildConfig.IMAGE_BASE_PATH + input.imageUrl,
            mediaRate = 0f,
            mediaDate = "",
            mediaType = com.devfalah.types.MediaType.MOVIE.value,
        )
    }
}