package com.karrar.movieapp.domain.mappers.movie

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.RatedMoviesDto
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.RatedMovies
import javax.inject.Inject

class RatedMoviesMapper @Inject constructor() : Mapper<RatedMoviesDto, RatedMovies> {
    override fun map(input: RatedMoviesDto): RatedMovies {
        return RatedMovies(
            id = input.id ?: 0,
            title = input.title ?: "",
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rating = input.rating ?: 0F,
            releaseDate = input.releaseDate ?: "",
        )
    }
}