package com.karrar.movieapp.domain.mappers.movie

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.PopularMovie
import com.thechance.repository.local.database.entity.movie.PopularMovieEntity
import javax.inject.Inject

class PopularMovieMapper @Inject constructor() : Mapper<PopularMovieEntity, PopularMovie> {
    override fun map(input: PopularMovieEntity): PopularMovie {
        return PopularMovie(
            movieID = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
            movieRate = input.movieRate,
            genre = input.genre,
        )
    }
}