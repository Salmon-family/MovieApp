package com.karrar.movieapp.data.repository.serchDataSource

import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.repository.BasePagingSource
import javax.inject.Inject
import kotlin.properties.Delegates

class SeriesSearchDataSource @Inject constructor(
    private val service: MovieService
) : BasePagingSource<TVShowsDTO>() {
    private var seriesSearchText by Delegates.notNull<String>()

    fun setSearchText(searchText: String) {
        seriesSearchText = searchText
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShowsDTO> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.searchForSeries(seriesSearchText, pageNumber)
            val pagedResponse = response.body()
            LoadResult.Page(
                data = pagedResponse?.items ?: emptyList(),
                prevKey = null,
                nextKey = if (pagedResponse?.items?.isEmpty() == true) null else pagedResponse?.page?.plus(
                    1
                )
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}