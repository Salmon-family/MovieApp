package com.karrar.movieapp.ui.allMedia

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.flowOf

class AllMediaPagingDataSource(private val repository: MovieRepository) :
    PagingSource<Int, Media>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1
        return try {
            val response = repository.getTrendingMovies2(pageNumber)

            LoadResult.Page(
                data = response,
                prevKey = if (response.isEmpty()) null else pageNumber - 1,
                nextKey = if (response.isEmpty()) null else pageNumber + 1,
                itemsBefore = LoadResult.Page.COUNT_UNDEFINED,
                itemsAfter = LoadResult.Page.COUNT_UNDEFINED
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition
    }
}