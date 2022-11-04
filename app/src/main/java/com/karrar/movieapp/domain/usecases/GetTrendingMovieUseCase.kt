package com.karrar.movieapp.domain.usecases

import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.usecases.home.mappers.ListMapper
import com.karrar.movieapp.domain.usecases.home.mappers.MovieMappersContainer
import com.thechance.repository.MovieRepository
import javax.inject.Inject


class GetTrendingMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMappersContainer: MovieMappersContainer,
) {
    suspend operator fun invoke(): List<Media> {
        return ListMapper(movieMappersContainer.itemListMapper)
            .mapList(movieRepository.getDailyTrending().items)
    }
}
