package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.actorDetailsDto.ActorMoviesDto
import com.karrar.movieapp.domain.models.Movie
import javax.inject.Inject

class ActorMoviesMapper @Inject constructor() : Mapper<ActorMoviesDto, Movie> {
    override fun map(input: ActorMoviesDto): Movie {
        return Movie(
            input.id,
            input.cast?.map { it?.posterPath }.toString()
        )
    }
}