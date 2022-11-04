package com.devfalah.usecases.home.mappers.movie

import com.thechance.remote.response.movie.MovieDetailsDto
import com.devfalah.usecases.mappers.Mapper
import com.thechance.repository.BuildConfig
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() :
    Mapper<MovieDetailsDto, com.devfalah.models.MovieDetails> {
    override fun map(input: MovieDetailsDto): com.devfalah.models.MovieDetails {
        return com.devfalah.models.MovieDetails(
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

fun Date.convertToDayMonthYearFormat(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(this)
}