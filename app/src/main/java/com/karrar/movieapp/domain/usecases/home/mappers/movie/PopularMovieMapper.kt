package com.karrar.movieapp.domain.usecases.home.mappers.movie

import com.thechance.local.database.entity.movie.PopularMovieEntity
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.PopularMovie
import javax.inject.Inject

class PopularMovieMapper @Inject constructor() : Mapper<PopularMovieEntity, PopularMovie> {
    override fun map(input: com.thechance.local.database.entity.movie.PopularMovieEntity): PopularMovie {
        return PopularMovie(
            movieID = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
            movieRate = input.movieRate,
            genre = input.genre,
        )
    }
}