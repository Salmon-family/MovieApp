package com.karrar.movieapp.domain.usecases.home.mappers.movie

import com.karrar.movieapp.BuildConfig
import com.thechance.local.database.entity.movie.AdventureMovieEntity
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject

class AdventureMovieMapper @Inject constructor() : Mapper<AdventureMovieEntity, Media> {
    override fun map(input: com.thechance.local.database.entity.movie.AdventureMovieEntity): Media {
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