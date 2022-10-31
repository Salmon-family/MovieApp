package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.domain.models.ActorMovie
import com.thechance.repository.remote.response.MovieDto
import javax.inject.Inject

class ActorMoviesMapper @Inject constructor() : Mapper<MovieDto, ActorMovie>{
    override fun map(input: MovieDto): ActorMovie {
        return ActorMovie(
            input.id ?: 0,
            (BuildConfig.IMAGE_BASE_PATH + input.posterPath),
        )
    }
}