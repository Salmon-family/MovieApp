package com.karrar.movieapp.domain.usecases.home.mappers.movie

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.movie.MovieDetailsDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.MovieDetails
import com.karrar.movieapp.utilities.convertToDayMonthYearFormat
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetails> {
    override fun map(input: com.thechance.remote.response.movie.MovieDetailsDto): MovieDetails {
        return MovieDetails(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.backdropPath,
            input.title ?: "",
            input.releaseDate?.convertToDayMonthYearFormat() ?: "",
            input.genres?.map { it?.name }?.joinToString(" , ") ?: "",
            input.runtime ?: 0,
            input.voteCount ?: 0,
            input.voteAverage.toString().take(3),
            input.overview ?: "",
        )
    }
}