package com.karrar.movieapp.data.local.mappers.movie

import com.karrar.movieapp.data.local.database.entity.movie.AdventureMovieEntity

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.mappers.Mapper
import javax.inject.Inject

class AdventureMovieMapper @Inject constructor() : Mapper<MovieDto, AdventureMovieEntity> {
    override fun map(input: MovieDto): AdventureMovieEntity {
        return AdventureMovieEntity(
            id = input.id ?: 0,
            name = input.originalTitle ?: "",
            imageUrl = input.posterPath ?: ""
        )
    }
}