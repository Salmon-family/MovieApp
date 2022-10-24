package com.karrar.movieapp.domain.usecase.movieDetails

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject

class GetSimilarMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke(movieId: Int): List<Media> {
        return movieRepository.getSimilarMovie(movieId)
    }
}