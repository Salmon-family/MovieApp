package com.thechance.repository.mapper.movie

import com.thechance.local.database.entity.movie.TrendingMovieEntity
import com.thechance.remote.response.MovieDto
import com.thechance.repository.mapper.Mapper
import javax.inject.Inject

class TrendingMovieMapper @Inject constructor() : Mapper<MovieDto, TrendingMovieEntity> {
    override fun map(input: MovieDto): TrendingMovieEntity {
        return TrendingMovieEntity(
            id = input.id ?: 0,
            name = input.originalTitle ?: "",
            imageUrl = input.posterPath ?: ""
        )
    }
}