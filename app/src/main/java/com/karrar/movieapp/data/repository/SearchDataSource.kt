package com.karrar.movieapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karrar.movieapp.data.mediaDataSource.BasePagingSource
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.movie.MovieMapper
import com.karrar.movieapp.domain.mappers.search.SearchActorMapper
import com.karrar.movieapp.domain.mappers.search.SearchSeriesMapper
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.TvShowDetails
import javax.inject.Inject
import kotlin.properties.Delegates

class SearchDataSourceContainer @Inject constructor(
    val movieSearchDataSource: MovieSearchDataSource,
    val seriesSearchDataSource: SeriesSearchDataSource,
    val actorSearchDataSource: ActorSearchDataSource
)

class MovieSearchDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: MovieMapper
) : BasePagingSource<MovieDto>(){
    private var movieSearchText by Delegates.notNull<String>()

    fun setSearchText(searchText: String){
        movieSearchText = searchText
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.searchForMovie(movieSearchText, pageNumber)
            val pagedResponse = response.body()
            LoadResult.Page(
                data = pagedResponse?.items ?: emptyList(),
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
) :  BasePagingSource<TVShowsDTO>(){
    private var seriesSearchText by Delegates.notNull<String>()

    fun setSearchText(searchText: String){
        seriesSearchText = searchText
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShowsDTO> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.searchForSeries(seriesSearchText, pageNumber)
            val pagedResponse = response.body()
            LoadResult.Page(
                data =pagedResponse?.items ?: emptyList(),
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
) :  BasePagingSource<ActorDto>(){
    private var actorSearchText by Delegates.notNull<String>()

    fun setSearchText(searchText: String){
        actorSearchText = searchText
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ActorDto> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.searchForActor(actorSearchText, pageNumber)
            val pagedResponse = response.body()
            LoadResult.Page(
                data =pagedResponse?.items ?: emptyList(),
                prevKey = null,
                nextKey = if(pagedResponse?.items?.isEmpty() == true) null else pagedResponse?.page?.plus(1)
            )
        }catch (e: Throwable){
            LoadResult.Error(e)
        }
    }
}