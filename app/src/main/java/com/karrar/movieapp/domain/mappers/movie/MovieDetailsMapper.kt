package com.karrar.movieapp.domain.mappers.movie

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.movie.MovieDetailsDto
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.MovieDetails
import com.karrar.movieapp.utilities.convertToDayMonthYearFormat
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetails> {
    override fun map(input: MovieDetailsDto): MovieDetails {
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
            MediaType.MOVIE
        )
    }
}