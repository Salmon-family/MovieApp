package com.karrar.movieapp.domain.mappers.movie

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.MovieDetails
import javax.inject.Inject

class WatchHistoryMapper @Inject constructor() : Mapper<MovieDetails, WatchHistoryEntity> {
    override fun map(input: MovieDetails): WatchHistoryEntity {
        return WatchHistoryEntity(
            id = input.movieId,
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.movieImage,
            movieTitle = input.movieName,
            voteAverage = input.movieVoteAverage,
            releaseDate = input.movieReleaseDate,
            movieDuration = input.movieDuration,
            mediaType = MediaType.MOVIE.value
        )
    }
}