package com.devfalah.usecases.home.mappers

import com.devfalah.models.ActorMovie
import com.devfalah.usecases.mappers.Mapper
import com.thechance.remote.response.MovieDto
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class ActorMoviesMapper @Inject constructor() : Mapper<MovieDto, ActorMovie> {
    override fun map(input: MovieDto): ActorMovie {
        return ActorMovie(
            input.id ?: 0,
            (BuildConfig.IMAGE_BASE_PATH + input.posterPath),
        )
    }
}