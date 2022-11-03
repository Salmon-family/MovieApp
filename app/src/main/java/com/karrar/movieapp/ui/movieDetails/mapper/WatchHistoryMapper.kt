package com.karrar.movieapp.ui.movieDetails.mapper

import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.WatchHistory
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.MovieDetailsUIState
import javax.inject.Inject

//class WatchHistoryMapper @Inject constructor() : Mapper<MovieDetailsUIState, WatchHistory> {
//    override fun map(input: MovieDetailsUIState): WatchHistory {
//        return WatchHistory(
//            id = input.id,
//            posterPath = input.image,
//            movieTitle = input.name,
//            movieDuration = input.duration,
//            voteAverage = input.voteAverage,
//            releaseDate = input.releaseDate,
//            mediaType = MediaType.MOVIE.name
//        )
//    }
//}