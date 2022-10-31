package com.example.usecase.mappers.movie

import com.example.models.models.MovieDetails
import com.example.models.utilities.MediaType
import com.example.usecase.mappers.Mapper
import com.example.usecase.utilites.convertToDayMonthYearFormat
import com.thechance.repository.BuildConfig
import com.thechance.repository.remote.response.movie.MovieDetailsDto
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetails> {
    override fun map(input: MovieDetailsDto): MovieDetails {
        return MovieDetails(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
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
