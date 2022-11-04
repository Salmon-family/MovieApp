package com.karrar.movieapp.domain.usecases.home.mappers.series

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.TVShowsDTO
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject

class TVShowMapper @Inject constructor() : Mapper<TVShowsDTO, Media> {
    override fun map(input: com.thechance.remote.response.TVShowsDTO): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            MediaType.TV_SHOW.value,
            input.originalName ?: "",
            input.firstAirDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0F,
        )
    }
}