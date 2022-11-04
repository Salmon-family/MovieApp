package com.devfalah.usecases.home.mappers.movie

import com.thechance.local.database.entity.movie.PopularMovieEntity
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.PopularMovie
import javax.inject.Inject

class PopularMovieMapper @Inject constructor() :
    Mapper<PopularMovieEntity, PopularMovie> {
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