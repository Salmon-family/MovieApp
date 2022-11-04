package com.devfalah.usecases.home.mappers.search

import com.devfalah.usecases.Constant
import com.thechance.remote.response.TVShowsDTO
import com.devfalah.usecases.mappers.Mapper
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class SearchSeriesMapper @Inject constructor() : Mapper<TVShowsDTO, com.devfalah.models.Media> {
    override fun map(input: com.thechance.remote.response.TVShowsDTO): com.devfalah.models.Media {
        return com.devfalah.models.Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.backdropPath,
            Constant.TV_SHOWS,
            input.originalName ?: "",
            input.firstAirDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0F,
        )
    }
}