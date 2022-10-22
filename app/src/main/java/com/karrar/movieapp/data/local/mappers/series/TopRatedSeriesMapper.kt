package com.karrar.movieapp.data.local.mappers.series

import com.karrar.movieapp.data.local.database.entity.series.TopRatedSeriesEntity
import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.domain.mappers.Mapper
import javax.inject.Inject

class TopRatedSeriesMapper @Inject constructor() : Mapper<TVShowsDTO, TopRatedSeriesEntity> {
    override fun map(input: TVShowsDTO): TopRatedSeriesEntity {
        return TopRatedSeriesEntity(
            id = input.id ?:0,
            name = input.originalName?:"",
            imageUrl = input.posterPath ?:""
        )
    }
}