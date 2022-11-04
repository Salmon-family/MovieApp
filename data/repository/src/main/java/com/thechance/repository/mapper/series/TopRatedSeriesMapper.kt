package com.thechance.repository.mapper.series

import com.thechance.local.database.entity.series.TopRatedSeriesEntity
import com.thechance.remote.response.TVShowsDTO
import com.thechance.repository.mapper.Mapper
import javax.inject.Inject

class TopRatedSeriesMapper @Inject constructor() : Mapper<TVShowsDTO, TopRatedSeriesEntity> {
    override fun map(input: TVShowsDTO): TopRatedSeriesEntity {
        return TopRatedSeriesEntity(
            id = input.id ?: 0,
            name = input.originalName ?: "",
            imageUrl = input.posterPath ?: ""
        )
    }
}