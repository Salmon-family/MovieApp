package com.karrar.movieapp.ui.movieDetails.mapper

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.WatchHistory
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.MovieDetailsUIState
import javax.inject.Inject

class WatchHistoryMapper @Inject constructor() : Mapper<MovieDetailsUIState, WatchHistory> {
    override fun map(input: MovieDetailsUIState): WatchHistory {
        return WatchHistory(
            id = input.id,
            posterPath = input.image,
            movieTitle = input.name,
            movieDuration = input.specialNumber,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            mediaType = input.mediaType.name
        )
    }
}