package com.thechance.repository.mapper.series

import com.thechance.local.database.entity.series.OnTheAirSeriesEntity
import com.thechance.remote.response.TVShowsDTO
import com.thechance.repository.mapper.Mapper
import javax.inject.Inject

class OnTheAirSeriesMapper @Inject constructor() :
    Mapper<TVShowsDTO, OnTheAirSeriesEntity> {
    override fun map(input: TVShowsDTO): OnTheAirSeriesEntity {
        return OnTheAirSeriesEntity(
            id = input.id ?: 0,
            name = input.originalName ?: "",
            imageUrl = input.posterPath ?: ""
        )
    }
}