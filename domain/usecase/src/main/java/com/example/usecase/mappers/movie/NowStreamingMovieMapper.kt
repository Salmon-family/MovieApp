package com.example.usecase.mappers.movie

import com.example.models.models.Media
import com.example.models.utilities.MediaType
import com.example.usecase.mappers.Mapper
import com.thechance.repository.BuildConfig
import com.thechance.repository.local.database.entity.movie.NowStreamingMovieEntity
import javax.inject.Inject

class NowStreamingMovieMapper @Inject constructor() : Mapper<NowStreamingMovieEntity, Media> {
    override fun map(input: NowStreamingMovieEntity): Media {
        return Media(
            mediaID = input.id,
            mediaName = input.name,
            mediaImage = BuildConfig.IMAGE_BASE_PATH + input.imageUrl,
            mediaRate = 0f,
            mediaDate = "",
            mediaType = MediaType.MOVIE.value
        )
    }
}
