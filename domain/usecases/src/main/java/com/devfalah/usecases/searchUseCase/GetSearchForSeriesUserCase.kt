package com.devfalah.usecases.searchUseCase

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.devfalah.models.Media
import com.devfalah.usecases.home.mappers.search.SearchSeriesMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetSearchForSeriesUserCase @Inject constructor(
    private val seriesRepository: com.thechance.repository.SeriesRepository,
    private val seriesMapper: SearchSeriesMapper
) {

    suspend operator fun invoke(searchTerm: String): Flow<PagingData<Media>> {
        return wrapper({ seriesRepository.searchForSeriesPager(searchTerm) }, seriesMapper::map)
    }

    private suspend fun <T : Any> wrapper(
        data: suspend () -> Pager<Int, T>,
        mapper: (T) -> Media,
    ): Flow<PagingData<Media>> {
        return data().flow.map { pager -> pager.map { mapper(it) } }
    }

}