package com.karrar.movieapp.data.local.mappers.series

import com.karrar.movieapp.data.local.database.entity.series.OnTheAirSeriesEntity
import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.domain.mappers.Mapper
import javax.inject.Inject

class OnTheAirSeriesMapper  @Inject constructor() : Mapper<TVShowsDTO, OnTheAirSeriesEntity> {
    override fun map(input: TVShowsDTO): OnTheAirSeriesEntity {
        return OnTheAirSeriesEntity(
            id = input.id ?:0,
            name = input.originalName?:"",
            imageUrl = input.posterPath ?:""
        )
    }
}