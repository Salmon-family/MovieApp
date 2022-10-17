package com.karrar.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.response.AddListResponse
import com.karrar.movieapp.data.remote.response.AddMovieDto
import com.karrar.movieapp.data.remote.response.MyListsDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.MovieMappersContainer
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.utilities.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val movieMappersContainer: MovieMappersContainer,
    private val searchDataSourceContainer: SearchDataSourceContainer
    ) : BaseRepository(), MovieRepository {

    override suspend fun getMovieGenreList(): List<Genre> {
        return wrap({ movieService.getGenreList() },
            { ListMapper(movieMappersContainer.genreMapper).mapList(it.genres) })
    }

    override suspend fun getAllMovies(): List<Media> {
        return wrap({ movieService.getAllMovies() },
            { ListMapper(movieMappersContainer.movieMapper).mapList(it.items) })
    }

    override suspend fun getPopularMovies(genres: List<Genre>): List<PopularMovie> {
        return wrap({ movieService.getPopularMovies() },
            { movieMappersContainer.popularMovieMapper.mapGenreMovie(it.items, genres) })
    }

    override suspend fun getTrendingMovies(page: Int): List<Media> {
        return wrap({ movieService.getTrendingMovies(page = page) },
            { ListMapper(movieMappersContainer.movieMapper).mapList(it.items) })
    }

    override suspend fun getDailyTrending(): List<Media> {
        return wrap({ movieService.getDailyTrending() },
            { ListMapper(movieMappersContainer.itemListMapper).mapList(it.items) })
    }

    override suspend fun getUpcomingMovies(page: Int): List<Media> {
        return wrap({ movieService.getUpcomingMovies(page = page) },
            { ListMapper(movieMappersContainer.movieMapper).mapList(it.items) })
    }

    override suspend fun getNowPlayingMovies(page: Int): List<Media> {
        return wrap({ movieService.getNowPlayingMovies(page = page) },
            { ListMapper(movieMappersContainer.movieMapper).mapList(it.items) })
    }

    override suspend fun getMovieListByGenreID(genreID: Int, page: Int): List<Media> {
        return wrap({ movieService.getMovieListByGenre(genreID = genreID, page = page) },
            { ListMapper(movieMappersContainer.movieMapper).mapList(it.items) })
    }

    /**
     * movie details
     * */

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return wrap({ movieService.getMovieDetails(movieId) },
            { movieMappersContainer.movieDetailsMapper.map(it) })
    }

    override suspend fun getMovieCast(movieId: Int): List<Actor> {
        return wrap({ movieService.getMovieCast(movieId) }, { response ->
            ListMapper(movieMappersContainer.actorMapper).mapList(response.cast)
        })
    }

    override suspend fun getSimilarMovie(movieId: Int): List<Media> {
        return wrap({ movieService.getSimilarMovie(movieId) }, { response ->
            ListMapper(movieMappersContainer.movieMapper).mapList(response.items)
        })
    }

    override suspend fun getMovieReviews(movieId: Int): List<Review> {
        return wrap({ movieService.getMovieReviews(movieId) }, { response ->
            ListMapper(movieMappersContainer.reviewMapper).mapList(response.items)
        })
    }

    override suspend fun getMovieTrailer(movieId: Int): Trailer {
        return wrap({ movieService.getMovieTrailer(movieId) },
            { movieMappersContainer.trailerMapper.map(it) })
    }

    override suspend fun getRatedMovie(
        accountId: Int,
        sessionId: String,
    ): List<RatedMovies> {
        return wrap({ movieService.getRatedMovie(accountId, sessionId) }, { response ->
            ListMapper(movieMappersContainer.ratedMoviesMapper).mapList(response.items)
        })
    }

    override suspend fun setRating(movieId: Int, value: Float, session_id: String): RatingDto {
        return wrap({ movieService.postRating(movieId, value, session_id) }, { it })
    }

    /**
     * Actor.
     * */
    override suspend fun getTrendingActors(): List<Actor> {
        return wrap({ movieService.getTrendingActors() },
            { ListMapper(movieMappersContainer.actorMapper).mapList(it.items) })
    }

    override suspend fun getActorDetails(actorId: Int): ActorDetails {
        return wrap({ movieService.getActorDetails(actorId) },
            { movieMappersContainer.actorDetailsMapper.map(it) })
    }

    override suspend fun getActorMovies(actorId: Int): List<Media> {
        return wrap({ movieService.getActorMovies(actorId) },
            { ListMapper(movieMappersContainer.movieMapper).mapList(it.cast) })
    }

    /**
     * My list
     * */

    override suspend fun getAllLists(
        accountId: Int,
        sessionId: String,
    ): List<CreatedList> {
        return wrap({ movieService.getCreatedLists(accountId, sessionId) },
            { ListMapper(movieMappersContainer.createdListsMapper).mapList(it.items) })
    }

    override suspend fun getListDetails(listId: Int): MyListsDto {
        return wrap({ movieService.getList(listId) }, { it })
    }

    override suspend fun getSavedListDetails(listId: String): List<SaveListDetails> {
        return wrap({ movieService.getList(listId.toInt()) },
            { ListMapper(movieMappersContainer.saveListDetailsMapper).mapList(it.items) })
    }

    override suspend fun createList(
        sessionId: String,
        name: String,
    ): AddListResponse {
        return wrap({ movieService.createList(sessionId, name) }, { it })
    }

    override suspend fun addMovieToList(
        sessionId: String,
        listId: Int,
        movieId: Int,
    ): AddMovieDto {
        return wrap({ movieService.addMovieToList(listId, sessionId, movieId) }, { it })
    }


    /**
     * searching
     * */

    override suspend fun searchForMovie(query: String): Pager<Int,Media> {
        val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)
        val dataSource = searchDataSourceContainer.movieSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = {dataSource})
    }

    override suspend fun searchForSeries(query: String): Pager<Int,Media> {
        val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)
        val dataSource = searchDataSourceContainer.seriesSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = {dataSource})
    }

    //should remove empty list ...
    override suspend fun searchForActor(query: String): Pager<Int,Media> {
        val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)
        val dataSource = searchDataSourceContainer.actorSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = {dataSource})
    }

    override fun getAllSearchHistory(): Flow<List<SearchHistory>> {
        return movieDao.getAllSearchHistory().map { response ->
            response.map { movieMappersContainer.searchHistoryMapper.map(it) }
        }
    }

    override suspend fun clearWatchHistory() {
        return movieDao.deleteAllWatchedMovies()
    }

    override suspend fun insertSearchItem(item: SearchHistoryEntity) {
        return movieDao.insert(item)
    }

    override suspend fun deleteSearchItem(item: SearchHistoryEntity) {
        return movieDao.delete(item)
    }

    override suspend fun insertMovie(movie: WatchHistoryEntity) {
        return movieDao.insert(movie)
    }

    override fun getAllWatchedMovies(): Flow<List<WatchHistoryEntity>> {
        return movieDao.getAllWatchedMovies()
    }
}