package com.karrar.movieapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.movie.MovieMapper
import com.karrar.movieapp.domain.mappers.search.SearchActorMapper
import com.karrar.movieapp.domain.mappers.search.SearchSeriesMapper
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject
import kotlin.properties.Delegates

class SearchDataSourceContainer @Inject constructor(
    val movieSearchDataSource: MovieSearchDataSource,
    val seriesSearchDataSource: SeriesSearchDataSource,
    val actorSearchDataSource: ActorSearchDataSource
)

abstract class SearchDataSource : PagingSource<Int, Media>() {
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media>

    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition
    }
}

class MovieSearchDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: MovieMapper
) : SearchDataSource(){
    private var movieSearchText by Delegates.notNull<String>()

    fun setSearchText(searchText: String){
        movieSearchText = searchText
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.searchForMovie(movieSearchText, pageNumber)
            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        }catch (e: Throwable){
            LoadResult.Error(e)
        }
    }
}

class SeriesSearchDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: SearchSeriesMapper
) : SearchDataSource(){
    private var seriesSearchText by Delegates.notNull<String>()

    fun setSearchText(searchText: String){
        seriesSearchText = searchText
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.searchForSeries(seriesSearchText, pageNumber)
            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        }catch (e: Throwable){
            LoadResult.Error(e)
        }
    }
}

class ActorSearchDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: SearchActorMapper
) : SearchDataSource(){
    private var actorSearchText by Delegates.notNull<String>()

    fun setSearchText(searchText: String){
        actorSearchText = searchText
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.searchForActor(actorSearchText, pageNumber)
            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        }catch (e: Throwable){
            LoadResult.Error(e)
        }
    }
}