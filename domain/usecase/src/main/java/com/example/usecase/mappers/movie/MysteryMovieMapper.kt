package com.example.usecase.mappers.movie

import com.example.models.models.Media
import com.example.models.utilities.MediaType
import com.example.usecase.mappers.Mapper
import com.thechance.repository.BuildConfig
import com.thechance.repository.local.database.entity.movie.MysteryMovieEntity
import javax.inject.Inject

class MysteryMovieMapper @Inject constructor() : Mapper<MysteryMovieEntity, Media> {
    override fun map(input: MysteryMovieEntity): Media {
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
