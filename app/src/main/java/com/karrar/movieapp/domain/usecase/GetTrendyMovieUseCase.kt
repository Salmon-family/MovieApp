package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject


class GetTrendyMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): List<Media> {
        return movieRepository.getDailyTrending()
    }
}
