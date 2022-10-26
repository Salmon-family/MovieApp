package com.karrar.movieapp.ui.movieDetails

import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.*

fun MediaDetails.toMediaDetailsUIState(): MediaDetailsUIState {
    return MediaDetailsUIState(
        id = this.id,
        image = this.image,
        name = this.name,
        releaseDate = this.releaseDate,
        genres = this.genres,
        specialNumber = this.specialNumber,
        review = this.review,
        voteAverage = this.voteAverage,
        overview = this.overview,
        mediaType = this.mediaType
    )
}

fun Actor.toActorUIState(): ActorUiState {
    return ActorUiState(
        actorID = this.actorID,
        actorImage = this.actorImage,
        actorName = this.actorName
    )
}

fun Media.toMediaUIState(): MediaUIState {
    return MediaUIState(
        mediaID = this.mediaID,
        mediaRate = this.mediaRate,
        mediaDate = this.mediaDate,
        mediaType = this.mediaType,
        mediaImage = this.mediaImage,
        mediaName = this.mediaName
    )
}

fun Rated.toRatedUIState(): RatedUIState {
    return RatedUIState(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath,
        rating = this.rating,
        releaseDate = this.releaseDate,
        mediaType = this.mediaType
    )
}

fun Review.toReviewUIState(): ReviewUIState {
    return ReviewUIState(
        content = this.content,
        createDate = this.createDate,
        user = this.user.toUserUIState(),
    )
}

fun User.toUserUIState(): UserUIState {
    return UserUIState(
        userImage = this.userImage,
        name = this.name,
        userName = this.userName,
        rating = this.rating
    )
}

fun RatingStatus.toRatingStatusUIState(): RatingUIState {
    return RatingUIState(
        statusCode = this.statusCode,
        statusMessage = this.statusMessage
    )
}