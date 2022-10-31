package com.thechance.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thechance.repository.remote.service.MovieService
import javax.inject.Inject
import kotlin.properties.Delegates
/*
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
            val pagedResponse = response.body()
            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = if(pagedResponse?.items?.isEmpty() == true) null else pagedResponse?.page?.plus(1)
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
            val pagedResponse = response.body()
            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = if(pagedResponse?.items?.isEmpty() == true) null else pagedResponse?.page?.plus(1)
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
            val pagedResponse = response.body()
            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = if(pagedResponse?.items?.isEmpty() == true) null else pagedResponse?.page?.plus(1)
            )
        }catch (e: Throwable){
            LoadResult.Error(e)
        }
    }
}*/