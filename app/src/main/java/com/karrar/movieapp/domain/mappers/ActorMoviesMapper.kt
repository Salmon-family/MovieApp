package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.actorDetailsDto.ActorMoviesDto
import com.karrar.movieapp.data.remote.response.actorDetailsDto.CastDto
import com.karrar.movieapp.domain.models.Movie
import javax.inject.Inject

class ActorMoviesMapper @Inject constructor() : Mapper<CastDto, Movie> {
    override fun map(input: CastDto): Movie {
        return Movie(
            input.id,
            input.posterPath,
        )
    }

}