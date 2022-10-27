package com.karrar.movieapp.ui.tvShowDetails.tvShowExtensionMapeprs

import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.*

fun TvShowDetails.toTvShowDetailsResultUIState(): TvShowDetailsResultUIState {
    return TvShowDetailsResultUIState(
        tvShowId = this.tvShowId,
        tvShowName = this.tvShowName,
        tvShowOverview = this.tvShowOverview,
        tvShowImage = this.tvShowImage,
        tvShowVoteAverage = this.tvShowVoteAverage,
        tvShowSeasonsNumber = this.tvShowSeasonsNumber,
        tvShowReview = this.tvShowReview,
        tvShowReleaseDate = this.tvShowReleaseDate,
        tvShowGenres = this.tvShowGenres,
    )
}

fun Actor.toCastUIState(): SeriesCastUIState {
    return SeriesCastUIState(
        actorID = actorID,
        actorImage = actorImage,
        actorName = actorName,
    )
}

fun Season.toTvShowSeasonUIState(): SeasonUIState {
    return SeasonUIState(
        seasonId = this.seasonId,
        seasonName = this.seasonName,
        seasonNumber = this.seasonNumber,
        imageUrl = this.imageUrl,
        episodeCount = this.episodeCount,
        seasonYear = this.seasonYear,
        seasonDescription = this.seasonDescription,
        episodes = this.episodes.map { episode ->
            EpisodeUIState(
                episodeId = episode.episodeId,
                episodeName = episode.episodeName,
                episodeNumber = episode.episodeNumber,
                imageUrl = episode.imageUrl,
                episodeTotalReviews = episode.episodeTotalReviews,
                episodeRate = episode.episodeRate,
                episodeDuration = episode.episodeDuration,
                episodeDate = episode.episodeDate,
                episodeDescription = episode.episodeDescription,
            )
        }
    )
}

fun Rated.toTvShowRatedUIState(): RatedUIState {
    return RatedUIState(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath,
        rating = this.rating,
        releaseDate = this.releaseDate,
        mediaType = this.mediaType,
    )
}

fun Review.toTvShowReviewUIState(): ReviewUIState {
    return ReviewUIState(
        content = this.content,
        createDate = this.createDate,
    )
}
