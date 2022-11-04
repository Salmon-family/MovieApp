package com.thechance.repository.mapper.movie

import com.thechance.local.database.entity.movie.NowStreamingMovieEntity
import com.thechance.remote.response.MovieDto
import com.thechance.repository.mapper.Mapper

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