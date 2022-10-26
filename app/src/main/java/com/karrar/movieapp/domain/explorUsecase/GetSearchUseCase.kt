package com.karrar.movieapp.domain.explorUsecase

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.MovieMappersContainer
import com.karrar.movieapp.domain.mappers.movie.MovieMapper
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.SearchHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMappersContainer: MovieMappersContainer,
    private val movieMapper: MovieMapper,
    ) {

    suspend operator fun invoke(): Flow<List<SearchHistory>> {
        return movieRepository.getAllSearchHistory().map { response ->
            response.map { movieMappersContainer.searchHistoryMapper.map(it) }
        }
    }

    suspend  fun getMovies(text: String): Flow<PagingData<Media>> {
        return wrapper({movieRepository.searchForMoviePager(text)}, movieMapper::map)
    }

    suspend  fun getTvShows(text: String): Flow<PagingData<Media>> {
        return wrapper({movieRepository.searchForSeriesPager(text)}, movieMappersContainer.seriesMapper::map)

    }

    suspend  fun getActors(text: String): Flow<PagingData<Media>> {
        return wrapper({movieRepository.searchForActorPager(text)}, movieMappersContainer.searchActorMapper::map)

    }

    private suspend fun <T : Any> wrapper(
        data: suspend () -> Pager<Int, T>,
        mapper: (T) -> Media,
    ): Flow<PagingData<Media>> {
        return data().flow.map { pager -> pager.map { mapper(it) } }
    }

}