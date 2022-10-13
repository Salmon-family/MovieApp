package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.movie.MovieDetailsDto
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.models.MovieDetails
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetails> {
    override fun map(input: MovieDetailsDto): MovieDetails {
        return MovieDetails(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            input.title ?: "",
            input.releaseDate?.take(4) ?: "unknown",
            input.genres?.map { it?.name }?.joinToString(" , ") ?: "unknown",
            input.runtime ?: 0,
            input.voteCount ?: 0,
            input.voteAverage.toString().take(3),
            input.overview ?: "",
            MediaType.MOVIE
        )
    }
}