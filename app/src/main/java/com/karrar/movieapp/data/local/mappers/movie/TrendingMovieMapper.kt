package com.karrar.movieapp.data.local.mappers.movie

import com.karrar.movieapp.data.local.database.entity.movie.TrendingMovieEntity
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.mappers.Mapper
import javax.inject.Inject

class TrendingMovieMapper @Inject constructor() : Mapper<MovieDto, TrendingMovieEntity> {
    override fun map(input: MovieDto): TrendingMovieEntity {
        return TrendingMovieEntity(
            id = input.id ?:0,
            name = input.originalTitle?:"",
            imageUrl = input.posterPath ?:""
        )
    }
}