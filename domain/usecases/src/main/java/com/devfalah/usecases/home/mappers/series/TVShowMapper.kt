package com.devfalah.usecases.home.mappers.series

import com.thechance.remote.response.TVShowsDTO
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Media
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class TVShowMapper @Inject constructor() : Mapper<TVShowsDTO, Media> {
    override fun map(input: TVShowsDTO): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            com.devfalah.types.MediaType.TV_SHOW.value,
            input.originalName ?: "",
            input.firstAirDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0F,
        )
    }
}