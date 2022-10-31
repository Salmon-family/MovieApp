package com.karrar.movieapp.domain.usecase


import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.MovieMappersContainer
import com.karrar.movieapp.domain.mappers.savedList.ItemListMapper
import com.karrar.movieapp.domain.models.Media
import com.thechance.repository.MovieRepository
import javax.inject.Inject

class GetTrendingMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val itemListMapper: ItemListMapper,
) {
    suspend operator fun invoke(): List<Media> {
        return ListMapper(itemListMapper)
            .mapList(movieRepository.getDailyTrending().items)
    }
}
