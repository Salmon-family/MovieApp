package com.karrar.movieapp.domain.usecases.home.mappers.movie

import com.karrar.movieapp.BuildConfig
import com.thechance.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.MovieDetails
import javax.inject.Inject

class WatchHistoryMapper @Inject constructor() : Mapper<MovieDetails, WatchHistoryEntity> {
    override fun map(input: MovieDetails): com.thechance.local.database.entity.WatchHistoryEntity {
        return com.thechance.local.database.entity.WatchHistoryEntity(
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