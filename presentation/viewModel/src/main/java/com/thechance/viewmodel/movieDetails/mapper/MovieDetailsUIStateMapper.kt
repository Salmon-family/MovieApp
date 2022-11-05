package com.thechance.viewmodel.movieDetails.mapper

import com.thechance.viewmodel.movieDetails.movieDetailsUIState.MovieDetailsUIState
import javax.inject.Inject

data class MovieDuration(val hours: Int, val minutes: Int)

class MovieDetailsUIStateMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.MovieDetails, MovieDetailsUIState> {
    override fun map(input: com.devfalah.models.MovieDetails): MovieDetailsUIState {
        val duration = formatMovieDuration(input.movieDuration)
        return MovieDetailsUIState(
            id = input.movieId,
            image = input.movieImage,
            name = input.movieName,
            releaseDate = input.movieReleaseDate,
            genres = input.movieGenres,
            hours = duration.hours,
            minutes = duration.minutes,
            specialNumber = input.movieDuration,
            review = input.movieReview,
            voteAverage = input.movieVoteAverage,
            overview = input.movieOverview,
        )
    }

    private fun formatMovieDuration(duration: Int): MovieDuration {
        return MovieDuration(hours = duration.div(60), minutes = duration.rem(60))
    }

}

