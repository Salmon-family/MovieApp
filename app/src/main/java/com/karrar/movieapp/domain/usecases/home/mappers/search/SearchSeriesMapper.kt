package com.karrar.movieapp.domain.usecases.home.mappers.search

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.TVShowsDTO
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class SearchSeriesMapper @Inject constructor() : Mapper<TVShowsDTO, Media> {
    override fun map(input: com.thechance.remote.response.TVShowsDTO): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.backdropPath,
            Constants.TV_SHOWS,
            input.originalName ?: "",
            input.firstAirDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0F,
        )
    }
}