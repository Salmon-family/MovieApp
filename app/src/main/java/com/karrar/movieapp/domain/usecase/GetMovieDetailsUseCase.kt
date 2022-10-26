package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.MovieMappersContainer
import com.karrar.movieapp.domain.models.*
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMappersContainer: MovieMappersContainer,
    private val getSessionIDUseCase: GetSessionIDUseCase,
) {
    suspend fun getMovieDetails(movieId: Int): MovieDetails? {
        return movieRepository.getMovieDetails(movieId)
            ?.let { movieMappersContainer.movieDetailsMapper.map(it) }
    }

    suspend fun getMovieCast(movieId: Int): List<Actor> {
        return ListMapper(movieMappersContainer.actorMapper).mapList(
            movieRepository.getMovieCast(
                movieId
            )?.cast
        )
    }

    suspend fun getMovieReviews(movieId: Int): List<Review> {
        return ListMapper(movieMappersContainer.reviewMapper).mapList(
            movieRepository.getMovieReviews(
                movieId
            )
        )

    }

    suspend fun getRatedMovie(accountId: Int): List<Rated> {
        return ListMapper(movieMappersContainer.ratedMoviesMapper).mapList(
            movieRepository.getRatedMovie(
                accountId, getSessionIDUseCase() ?: ""
            )
        )
    }

    suspend fun getSimilarMovie(movieId: Int): List<Media> {
        return ListMapper(movieMappersContainer.movieMapper).mapList(
            movieRepository.getSimilarMovie(
                movieId
            )
        )
    }

    suspend fun insertMovie(movie: WatchHistoryEntity) {
        return movieRepository.insertMovie(movie)
    }

    suspend fun setRating(movieId: Int, value: Float): RatingStatus? {
        return movieRepository.setRating(movieId, value, getSessionIDUseCase() ?: "")?.let {
            movieMappersContainer.ratingStatusMoviesMapper
                .map(it)
        }
    }

}