package com.devfalah.usecases.home.mappers.movie

import com.thechance.local.database.entity.movie.TrendingMovieEntity
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Media
import com.devfalah.types.MediaType
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class TrendingMovieMapper @Inject constructor() :
    Mapper<TrendingMovieEntity, Media> {
    override fun map(input: TrendingMovieEntity): Media {
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