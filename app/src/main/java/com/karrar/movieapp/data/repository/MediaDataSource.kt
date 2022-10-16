package com.karrar.movieapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.movie.MovieMapper
import com.karrar.movieapp.domain.mappers.series.TVShowMapper
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject
import kotlin.properties.Delegates

class MediaDataSourceContainer @Inject constructor(
    val trendingMovieDataSource: TrendingMovieDataSource,
    val upcomingMovieMovieDataSource: UpcomingMovieMovieDataSource,
    val nowStreamingMovieMovieDataSource: NowStreamingMovieMovieDataSource,
    val airingTodayTvShowDataSource: AiringTodayTvShowDataSource,
    val popularTvShowDataSource: PopularTvShowDataSource,
    val topRatedTvShowDataSource: TopRatedTvShowDataSource,
    val movieGenreShowDataSource: MovieGenreShowDataSource,
    val onTheAirTvShowDataSource: OnTheAirTvShowDataSource,
    val actorMovieDataSource: ActorMovieDataSource
)

abstract class MediaDataSource : PagingSource<Int, Media>() {

    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media>

    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition
    }
}

class TrendingMovieDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: MovieMapper
) : MediaDataSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getTrendingMovies(page = pageNumber)

            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}

class UpcomingMovieMovieDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: MovieMapper
) : MediaDataSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getUpcomingMovies(page = pageNumber)

            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}

class NowStreamingMovieMovieDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: MovieMapper
) : MediaDataSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getNowPlayingMovies(page = pageNumber)

            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}

class AiringTodayTvShowDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: TVShowMapper
) : MediaDataSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getAiringToday(page = pageNumber)

            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}

class PopularTvShowDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: TVShowMapper
) : MediaDataSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getPopularTvShow(page = pageNumber)

            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}

class TopRatedTvShowDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: TVShowMapper
) : MediaDataSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getTopRatedTvShow(page = pageNumber)

            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}

class OnTheAirTvShowDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: TVShowMapper
) : MediaDataSource() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getOnTheAir(page = pageNumber)

            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}

class MovieGenreShowDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: MovieMapper
) : MediaDataSource() {

    private var movieGenreID by Delegates.notNull<Int>()

    fun setGenre(genreID: Int) {
        movieGenreID = genreID
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getMovieListByGenre(movieGenreID, pageNumber)
            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}

class ActorMovieDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: MovieMapper
) : MediaDataSource() {

    private var actorID by Delegates.notNull<Int>()

    fun setMovieActorID(actor: Int) {
        actorID = actor
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {

        return try {
            val response = service.getActorMovies(actorID)
            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.cast),
                prevKey = null,
                nextKey = null
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}

