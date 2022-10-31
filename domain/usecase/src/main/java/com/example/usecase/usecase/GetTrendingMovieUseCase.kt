package com.example.usecase.usecase

import com.example.models.models.Media
import com.example.usecase.mappers.ListMapper
import com.example.usecase.mappers.savedList.ItemListMapper
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
