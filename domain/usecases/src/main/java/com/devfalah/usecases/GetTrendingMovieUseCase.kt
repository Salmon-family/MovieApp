package com.devfalah.usecases

import com.devfalah.models.Media
import com.devfalah.usecases.home.mappers.ListMapper
import com.devfalah.usecases.home.mappers.MovieMappersContainer
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
