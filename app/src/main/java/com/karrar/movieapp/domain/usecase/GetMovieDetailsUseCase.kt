package com.karrar.movieapp.domain.usecase

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
    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = movieRepository.getMovieDetails(movieId)
        return if (response != null) {
            movieMappersContainer.movieDetailsMapper.map(response)
        } else {
            throw Throwable("Not Success")
        }
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
}