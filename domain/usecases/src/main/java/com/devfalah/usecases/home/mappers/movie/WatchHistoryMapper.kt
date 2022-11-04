package com.devfalah.usecases.home.mappers.movie

import com.thechance.local.database.entity.WatchHistoryEntity
import com.devfalah.types.MediaType
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.MovieDetails
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class WatchHistoryMapper @Inject constructor() :
    Mapper<MovieDetails, WatchHistoryEntity> {
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