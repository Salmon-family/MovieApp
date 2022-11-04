package com.thechance.repository.mapper.series

import com.thechance.local.database.entity.series.AiringTodaySeriesEntity
import com.thechance.remote.response.TVShowsDTO
import com.thechance.repository.mapper.Mapper
import javax.inject.Inject

class AiringTodaySeriesMapper @Inject constructor() :
    Mapper<TVShowsDTO, AiringTodaySeriesEntity> {
    override fun map(input: TVShowsDTO): AiringTodaySeriesEntity {
        return AiringTodaySeriesEntity(
            id = input.id ?: 0,
            name = input.originalName ?: "",
            imageUrl = input.posterPath ?: ""
        )
    }
}