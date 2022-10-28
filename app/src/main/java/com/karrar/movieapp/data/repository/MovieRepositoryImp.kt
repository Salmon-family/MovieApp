package com.karrar.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.karrar.movieapp.data.local.AppConfiguration
import com.karrar.movieapp.data.local.database.daos.ActorDao
import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.local.mappers.movie.LocalMovieMappersContainer
import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.data.repository.mediaDataSource.ActorMovieDataSource
import com.karrar.movieapp.data.remote.response.AddListResponse
import com.karrar.movieapp.data.remote.response.AddMovieDto
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.response.MyListsDto
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.data.remote.response.actor.ActorMoviesDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.repository.mediaDataSource.movie.MovieDataSourceContainer
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.MediaDataSourceContainer
import com.karrar.movieapp.domain.mappers.MovieMappersContainer
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.utilities.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val actorDao: ActorDao,
    private val movieMappersContainer: MovieMappersContainer,
    private val dataMappers: LocalMovieMappersContainer,
    private val appConfiguration: AppConfiguration,
    private val actorDataSource: ActorDataSource,
    private val mediaDataSourceContainer: MediaDataSourceContainer,
    private val searchDataSourceContainer: SearchDataSourceContainer,
    private val movieMovieDataSource: MovieDataSourceContainer,
    private val actorMovieDataSource: ActorMovieDataSource,
) : BaseRepository(), MovieRepository {

    override suspend fun getMovieGenreList(): List<Genre> {
        return wrap({ movieService.getGenreList() },
            { ListMapper(movieMappersContainer.genreMapper).mapList(it.genres) })
    }

    override suspend fun getMovieGenreList2(): List<GenreDto>? {
        return movieService.getGenreList().body()?.genres
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
        sessionId: String, ): List<RatedMoviesDto>? {
        return movieService.getRatedMovie(accountId, sessionId).body()?.items
    }

    override suspend fun setRating(movieId: Int, value: Float, session_id: String): RatingDto {
        return wrap({ movieService.postRating(movieId, value, session_id) }, { it })
    }

    /**
     * Actor.
     * */
    override suspend fun getTrendingActors(page: Int): List<Actor> {
        return wrap({ movieService.getTrendingActors() },
            { ListMapper(movieMappersContainer.actorMapper).mapList(it.items) })
    }

    override suspend fun getActorDetails(actorId: Int): ActorDto? {
        return movieService.getActorDetails(actorId = actorId).body()
    }

    override suspend fun getActorMovies(actorId: Int): ActorMoviesDto? {
        return movieService.getActorMovies(actorId = actorId).body()
    }

    /**
     * My list
     * */

    override suspend fun getAllLists(
        sessionId: String,
    ): List<CreatedListDto>? {
        return movieService.getCreatedLists(sessionId = sessionId).body()?.items
    }

    override suspend fun getListDetails(listId: Int): MyListsDto? {
        return movieService.getList(listId).body()
    }

    override suspend fun getSavedListDetails(listId: Int): List<SavedListDto>? {
        return movieService.getList(listId).body()?.items
    }

    override suspend fun createList(
        sessionId: String,
        name: String,
    ): AddListResponse? {
        return movieService.createList(sessionId, name).body()
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

    override fun searchForMovie(query: String): Flow<PagingData<Media>> {
        val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)
        val dataSource = searchDataSourceContainer.movieSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = {dataSource}).flow
    }

    override fun searchForSeries(query: String): Flow<PagingData<Media>> {
        val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)
        val dataSource = searchDataSourceContainer.seriesSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = {dataSource}).flow
    }

    //should remove empty list ...
    override fun searchForActor(query: String): Flow<PagingData<Media>> {
        val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)
        val dataSource = searchDataSourceContainer.actorSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = {dataSource}).flow
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


    override suspend fun getActorData(): Pager<Int, ActorDto> {
        return Pager(config = config, pagingSourceFactory = { actorDataSource })
    }

    override suspend fun getAllMovies(): Pager<Int, MovieDto> {
        return Pager(
            config = config,
            pagingSourceFactory = { mediaDataSourceContainer.movieDataSource })
    }

    override suspend fun getMovieByGenre(genreID: Int): Pager<Int, MovieDto> {
        return Pager(
            config = config,
            pagingSourceFactory = {
                val dataSource = mediaDataSourceContainer.movieByGenreDataSource
                dataSource.setGenre(genreID)
                dataSource
            }
        )
    }


    /**
     * Caching
     * */

    override fun getPopularMovies(): Flow<List<PopularMovie>> {
        return movieDao.getPopularMovies().map { list ->
            list.map { movieMappersContainer.popularMovieEntityMapper.map(it) }
        }
    }

    override fun getTrendingMovies(): Flow<List<Media>> {
        return movieDao.getTrendingMovies().map { list ->
            list.map { movieMappersContainer.trendingMapper.map(it) }
        }
    }

    override fun getNowPlayingMovies(): Flow<List<Media>> {
        return movieDao.getNowStreamingMovies().map { list ->
            list.map { movieMappersContainer.nowStreamingMovieMapper.map(it) }
        }
    }

    override fun getUpcomingMovies(): Flow<List<Media>> {
        return movieDao.getUpcomingMovies().map { list ->
            list.map { movieMappersContainer.upcomingMovieMapper.map(it) }
        }
    }

    override fun getAdventureMovies(): Flow<List<Media>> {
        return movieDao.getAdventureMovies().map { list ->
            list.map { movieMappersContainer.adventureMovieMapper.map(it) }
        }
    }

    override fun getMysteryMovies(): Flow<List<Media>> {
        return movieDao.getMysteryMovies().map { list ->
            list.map { movieMappersContainer.mysteryMovieMapper.map(it) }
        }
    }

    override suspend fun refreshPopularMovies() {
        val genres = getMovieGenreList()
        refreshWrapper(
            { movieService.getPopularMovies() },
            { items ->
                items?.map { dataMappers.popularMovieMapper.map(it, genres) }
            },
            {
                movieDao.deletePopularMovies()
                movieDao.insertPopularMovies(it)
            },
        )

    }

    override suspend fun refreshTrendingMovies() {
        refreshWrapper(
            { movieService.getTrendingMovies() },
            { list ->
                list?.map { dataMappers.trendingMovieMapper.map(it) }
            },
            {
                movieDao.deleteAllTrendingMovies()
                movieDao.insertTrendingMovie(it)
            },
        )
    }

    override suspend fun refreshNowPlayingMovies() {
        refreshWrapper(
            { movieService.getNowPlayingMovies() },
            { list ->
                list?.map { dataMappers.nowStreamingMovieMapper.map(it) }
            },
            {
                movieDao.deleteAllNowStreamingMovies()
                movieDao.insertNowStreamingMovie(it)
            },
        )
    }

    override suspend fun refreshUpcomingMovies() {
        refreshWrapper({ movieService.getUpcomingMovies() }, { list ->
            list?.map { dataMappers.upcomingMovieMapper.map(it) }
        }, {
            movieDao.deleteAllUpcomingMovies()
            movieDao.insertUpcomingMovie(it)
        })
    }

    override suspend fun refreshAdventureMovies() {
        refreshWrapper(
            { movieService.getMovieListByGenre(genreID = Constants.ADVENTURE_ID) },
            { list ->
                list?.map { dataMappers.adventureMovieMapper.map(it) }
            },
            {
                movieDao.deleteAllAdventureMovies()
                movieDao.insertAdventureMovie(it)
            },
        )
    }

    override suspend fun refreshMysteryMovies() {
        refreshWrapper(
            { movieService.getMovieListByGenre(genreID = Constants.MYSTERY_ID) },
            { list ->
                list?.map { dataMappers.mysteryMovieMapper.map(it) }
            },
            {
                movieDao.deleteAllMysteryMovies()
                movieDao.insertMysteryMovie(it)
            },
        )
    }

    override suspend fun refreshTrendingActors() {
        refreshWrapper(
            { movieService.getTrendingActors() }, { items ->
                items?.map { dataMappers.actorMapper.map(it) }
            }, {
                actorDao.deleteActors()
                actorDao.insertActors(it)
            }
        )
    }

    override suspend fun getTrendingMoviesPager(): Pager<Int, MovieDto> {
        return  Pager(config = config,pagingSourceFactory = {movieMovieDataSource.trendingMovieDataSource})
    }

    override suspend fun getNowPlayingMoviesPager(): Pager<Int, MovieDto> {
        return  Pager(config = config,pagingSourceFactory = {movieMovieDataSource.nowStreamingMovieMovieDataSource})
    }

    override suspend fun getUpcomingMoviesPager(): Pager<Int, MovieDto> {
        return  Pager(config = config,pagingSourceFactory = {movieMovieDataSource.upcomingMovieMovieDataSource})
    }

    override suspend fun getActorMoviesPager(actorId: Int): Pager<Int, MovieDto> {
        val dataSource = actorMovieDataSource
        dataSource.setMovieActorID(actorId)
        return  Pager(config = config,pagingSourceFactory = {dataSource})
    }

    override suspend fun saveRequestDate(value: Long) {
        appConfiguration.saveRequestDate(value)
    }

    override suspend fun getRequestDate(): Long? {
        return appConfiguration.getRequestDate()
    }

    override fun getTrendingActors(): Flow<List<Actor>> {
        return actorDao.getActors()
            .map { list -> list.map { movieMappersContainer.actorEntityMapper.map(it) } }
    }


}