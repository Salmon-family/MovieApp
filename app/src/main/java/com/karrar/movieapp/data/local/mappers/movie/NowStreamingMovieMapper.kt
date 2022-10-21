package com.karrar.movieapp.data.local.mappers.movie

import com.karrar.movieapp.data.local.database.entity.movie.NowStreamingMovieEntity
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.mappers.Mapper
import javax.inject.Inject

class NowStreamingMovieMapper @Inject constructor() : Mapper<MovieDto, NowStreamingMovieEntity> {
    override fun map(input: MovieDto): NowStreamingMovieEntity {
        return NowStreamingMovieEntity(
            id = input.id ?: 0,
            name = input.originalTitle ?: "",
            imageUrl = input.posterPath ?: ""
        )
    }
}