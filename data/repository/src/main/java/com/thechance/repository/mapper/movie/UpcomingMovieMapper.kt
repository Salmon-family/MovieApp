package com.thechance.repository.mapper.movie

import com.thechance.local.database.entity.movie.UpcomingMovieEntity
import com.thechance.remote.response.MovieDto
import com.thechance.repository.mapper.Mapper
import javax.inject.Inject

class UpcomingMovieMapper @Inject constructor() : Mapper<MovieDto, UpcomingMovieEntity> {
    override fun map(input: MovieDto): UpcomingMovieEntity {
        return UpcomingMovieEntity(
            id = input.id ?: 0,
            name = input.originalTitle ?: "",
            imageUrl = input.posterPath ?: ""
        )
    }
}