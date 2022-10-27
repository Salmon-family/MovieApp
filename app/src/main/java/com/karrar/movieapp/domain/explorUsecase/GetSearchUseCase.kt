package com.karrar.movieapp.domain.explorUsecase

import androidx.paging.*
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.MovieMappersContainer
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.ui.search.MediaTypes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetSearchUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMappersContainer: MovieMappersContainer,
    ) {

    suspend operator fun invoke(): Flow<List<SearchHistory>> {
        return movieRepository.getAllSearchHistory().map { response ->
            response.map { movieMappersContainer.searchHistoryMapper.map(it) }
        }
    }

    suspend fun getSearchResult(mediaTypes: MediaTypes,text: String): Flow<PagingData<Media>>{
        return when(mediaTypes){
            MediaTypes.MOVIE ->  wrapper({movieRepository.searchForMoviePager(text)}, movieMappersContainer.movieMapper::map)
            MediaTypes.TVS_SHOW ->  wrapper({movieRepository.searchForSeriesPager(text)}, movieMappersContainer.seriesMapper::map)
            MediaTypes.ACTOR ->  wrapper({movieRepository.searchForActorPager(text)}, movieMappersContainer.searchActorMapper::map)
        }
    }

    suspend fun saveSearchResult(id: Int, name: String) {
        movieRepository.insertSearchItem(
            SearchHistoryEntity(
                id = id.toLong(),
                search = name
            )
        )
    }

    private suspend fun <T : Any> wrapper(
        data: suspend () -> Pager<Int, T>,
        mapper: (T) -> Media,
    ): Flow<PagingData<Media>> {
        return data().flow.map { pager -> pager.map { mapper(it) } }
    }

}