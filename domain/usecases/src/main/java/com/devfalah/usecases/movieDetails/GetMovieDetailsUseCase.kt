package com.devfalah.usecases.movieDetails


import com.devfalah.types.MediaType
import com.devfalah.usecases.GetReviewsUseCase
import com.devfalah.usecases.home.mappers.actor.ActorDtoMapper
import com.devfalah.usecases.home.mappers.movie.MovieDetailsMapper
import com.devfalah.usecases.home.mappers.movie.MovieMapper
import com.thechance.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val getMovieReviewsUseCase: GetReviewsUseCase,
    private val actorMapper: ActorDtoMapper,
    private val movieMapper: MovieMapper
) {
    suspend fun getMovieDetails(movieId: Int): com.devfalah.models.MovieDetails {
        val response = movieRepository.getMovieDetails(movieId)
        return response?.let {
            movieDetailsMapper.map(response)
        } ?: throw Throwable("Not Success")
    }

    suspend fun getMovieCast(movieId: Int): List<com.devfalah.models.Actor> {
        return movieRepository.getMovieCast(movieId)?.cast?.let {
            it.map { actorMapper.map(it) }
        } ?: throw Throwable("Not Success")
    }

    suspend fun getMovieReviews(movieId: Int): com.devfalah.models.MediaDetailsReviews {
        val reviews = getMovieReviewsUseCase(MediaType.MOVIE, movieId)
        val MAX_NUM_REVIEWS = 3
        return com.devfalah.models.MediaDetailsReviews(reviews.take(MAX_NUM_REVIEWS),
            reviews.size > MAX_NUM_REVIEWS)
    }

    suspend fun getSimilarMovie(movieId: Int): List<com.devfalah.models.Media> {
        return movieRepository.getSimilarMovie(movieId)?.let {
            it.map { movieMapper.map(it) }
        } ?: throw Throwable("Not Success")
    }
}