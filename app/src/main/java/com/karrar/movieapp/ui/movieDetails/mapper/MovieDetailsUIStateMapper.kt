package com.karrar.movieapp.ui.movieDetails.mapper

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.MovieDetails
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.MovieDetailsUIState
import javax.inject.Inject

class MovieDetailsUIStateMapper @Inject constructor() : Mapper<MovieDetails, MovieDetailsUIState> {
    override fun map(input: MovieDetails): MovieDetailsUIState {
        return MovieDetailsUIState(
            id = input.id,
            image = input.image,
            name = input.name,
            releaseDate = input.releaseDate,
            genres = input.genres,
            duration = input.movieDuration,
            specialNumber = input.specialNumber,
            review = input.review,
            voteAverage = input.voteAverage,
            overview = input.overview,
            mediaType = input.mediaType
        )
    }
}