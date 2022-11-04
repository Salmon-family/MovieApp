package com.devfalah.usecases.home.mappers.movie

import com.devfalah.models.Media
import com.thechance.local.database.entity.movie.AdventureMovieEntity
import com.devfalah.usecases.mappers.Mapper
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class AdventureMovieMapper @Inject constructor() :
    Mapper<AdventureMovieEntity, Media> {
    override fun map(input: AdventureMovieEntity): Media {
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