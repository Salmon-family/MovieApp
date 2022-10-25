package com.karrar.movieapp.ui.movieDetails

import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.models.*

data class MovieDetailsUIState(
    val movieId: Int = 0,
    val movieDetailsResult: MovieDetailsResultUIState = MovieDetailsResultUIState(),
    val movieCastResult: List<ActorUiState> = emptyList(),
    val similarMoviesResult: List<MediaUIState> = emptyList(),
    val sessionIdResult: String? = "",
    val movieGetRatedResult: List<RatedUIState> = emptyList(),
    val movieReview: List<ReviewUIState> = emptyList(),
    val movieSetRatedResult: RatingUIState = RatingUIState(),
    val detailItemResult: MutableList<DetailItemUIState> = mutableListOf(),
    val isLoading: Boolean = false,
    val errors: List<Error> = emptyList(),
)

data class MovieDetailsResultUIState(
    val movieId: Int = 0,
    val movieImage: String = "",
    val movieName: String = "",
    val movieReleaseDate: String = "",
    val movieGenres: String = "",
    val movieDuration: Int = 0,
    val movieReview: Int = 0,
    val movieVoteAverage: String = "",
    val movieOverview: String = "",
    val movieType: MediaType = MediaType.MOVIE
) : MediaDetailsUIState(
    movieId,
    movieImage,
    movieName,
    movieReleaseDate,
    movieGenres,
    movieDuration,
    movieReview,
    movieVoteAverage,
    movieOverview,
    emptyList(),
    movieType
)

abstract class MediaDetailsUIState(
    val id: Int,
    val image: String,
    val name: String,
    val releaseDate: String,
    val genres: String,
    val specialNumber: Int,
    val review: Int,
    val voteAverage: String,
    val overview: String,
    val seasons: List<Season>,
    val mediaType: MediaType
)

data class ActorUiState(
    val actorID: Int = 0, val actorImage: String = "", val actorName: String = ""
)

data class MediaUIState(
    val mediaID: Int = 0,
    val mediaImage: String = "",
    val mediaType: String = "",
    val mediaName: String = "",
    val mediaDate: String = "",
    val mediaRate: Float = 0F,
)

data class RatedUIState(
    val id: Int = 0,
    val title: String = "",
    val posterPath: String = "",
    val rating: Float = 0F,
    val releaseDate: String = "",
    var mediaType: String = "",
)

data class ReviewUIState(
    val content: String = "",
    val createDate: String = "",
)

data class RatingUIState(
    val statusCode: Int = 0,
    val statusMessage: String = ""
)

sealed class DetailItemUIState(val priority: Int) {

    class Header(val data: MediaDetailsUIState) : DetailItem(0)

    class Cast(val data: List<ActorUiState>) : DetailItem(1)

    class SimilarMovies(val data: List<MediaUIState>) : DetailItem(2)

    class Comment(val data: ReviewUIState) : DetailItem(6)

}

data class Error(
    val code: Int = 0,
    val message: String = "",
)