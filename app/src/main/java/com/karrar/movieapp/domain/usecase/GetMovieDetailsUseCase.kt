package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.MovieMappersContainer
import com.karrar.movieapp.domain.models.*
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val accountRepository: AccountRepository,
    private val movieMappersContainer: MovieMappersContainer,
) {
    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return movieMappersContainer.movieDetailsMapper.map(movieRepository.getMovieDetails(movieId))
    }

    suspend fun getMovieCast(movieId: Int): List<Actor> {
        return ListMapper(movieMappersContainer.actorMapper).mapList(
            movieRepository.getMovieCast(
                movieId
            ).cast
        )
    }

    suspend fun getMovieReviews(movieId: Int): List<Review> {
        return ListMapper(movieMappersContainer.reviewMapper).mapList(
            movieRepository.getMovieReviews(
                movieId
            ).items
        )

    }

    suspend fun getRatedMovie(accountId: Int, sessionId: String): List<Rated> {
        return ListMapper(movieMappersContainer.ratedMoviesMapper).mapList(
            movieRepository.getRatedMovie(
                accountId, sessionId
            ).items
        )
    }

    fun getSessionId(): String? {
        return accountRepository.getSessionId()
    }

    suspend fun getSimilarMovie(movieId: Int): List<Media> {
        return ListMapper(movieMappersContainer.movieMapper).mapList(
            movieRepository.getSimilarMovie(
                movieId
            ).items
        )
    }

    suspend fun insertMovie(movie: WatchHistoryEntity) {
        return movieRepository.insertMovie(movie)
    }

    suspend fun setRating(movieId: Int, value: Float, session_id: String): RatingDto {
        return movieRepository.setRating(movieId, value, session_id)
    }

}