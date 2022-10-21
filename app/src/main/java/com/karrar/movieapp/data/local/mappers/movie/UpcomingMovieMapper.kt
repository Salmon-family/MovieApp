package com.karrar.movieapp.data.local.mappers.movie

import com.karrar.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.mappers.Mapper
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