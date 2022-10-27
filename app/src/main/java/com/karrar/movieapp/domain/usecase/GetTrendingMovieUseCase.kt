package com.karrar.movieapp.domain.usecase


import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.*
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject


class GetTrendingMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMappersContainer: MovieMappersContainer,
) {
    suspend operator fun invoke(): List<Media> {
        return  ListMapper(movieMappersContainer.itemListMapper)
            .mapList(movieRepository.getDailyTrending().items)
    }
}
