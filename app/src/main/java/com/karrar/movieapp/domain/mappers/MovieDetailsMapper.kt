package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.movieDetailsDto.MovieDetailsDto
import com.karrar.movieapp.domain.models.MovieDetails
import javax.inject.Inject

class MovieDetailsMapper  @Inject constructor():Mapper<MovieDetailsDto, MovieDetails> {
    override fun map(input: MovieDetailsDto): MovieDetails {
        return MovieDetails(
            input.id,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            input.title,
            input.releaseDate?.take(4),
            input.genres?.map { it?.name }?.joinToString(" , "),
            input.runtime,
            input.voteCount,
            input.voteAverage.toString().take(3),
            input.overview
        )
    }
}