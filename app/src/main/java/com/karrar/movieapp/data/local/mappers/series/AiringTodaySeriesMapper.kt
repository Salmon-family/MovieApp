package com.karrar.movieapp.data.local.mappers.series

import com.karrar.movieapp.data.local.database.entity.series.AiringTodaySeriesEntity
import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.domain.mappers.Mapper
import javax.inject.Inject

class AiringTodaySeriesMapper @Inject constructor() : Mapper<TVShowsDTO, AiringTodaySeriesEntity> {
    override fun map(input: TVShowsDTO): AiringTodaySeriesEntity {
        return AiringTodaySeriesEntity(
            id = input.id ?: 0,
            name = input.originalName ?: "",
            imageUrl = input.posterPath ?: ""
        )
    }
}