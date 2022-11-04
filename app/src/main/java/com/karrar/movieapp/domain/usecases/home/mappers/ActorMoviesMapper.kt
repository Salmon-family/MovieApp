package com.karrar.movieapp.domain.usecases.home.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.thechance.remote.response.MovieDto
import com.karrar.movieapp.domain.models.ActorMovie
import javax.inject.Inject

class ActorMoviesMapper @Inject constructor() : Mapper<MovieDto, ActorMovie> {
    override fun map(input: com.thechance.remote.response.MovieDto): ActorMovie {
        return ActorMovie(
            input.id ?: 0,
            (BuildConfig.IMAGE_BASE_PATH + input.posterPath),
        )
    }
}