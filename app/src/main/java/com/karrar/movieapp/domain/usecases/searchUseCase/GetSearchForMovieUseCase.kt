package com.karrar.movieapp.domain.usecases.searchUseCase

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.usecases.home.mappers.movie.MovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetSearchForMovieUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val movieMapper: MovieMapper
) {

    suspend operator fun invoke(searchTerm: String): Flow<PagingData<Media>> {
        return wrapper({ movieRepository.searchForMoviePager(searchTerm) }, movieMapper::map)
    }

    private suspend fun <T : Any> wrapper(
        data: suspend () -> Pager<Int, T>,
        mapper: (T) -> Media,
    ): Flow<PagingData<Media>> {
        return data().flow.map { pager -> pager.map { mapper(it) } }
    }

}