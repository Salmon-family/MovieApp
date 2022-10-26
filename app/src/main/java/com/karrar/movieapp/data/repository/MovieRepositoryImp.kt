package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.AppConfiguration
import com.karrar.movieapp.data.local.database.daos.ActorDao
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.local.mappers.movie.LocalMovieMappersContainer
import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.data.remote.response.movie.MovieDetailsDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.response.review.ReviewsDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.enums.AllMediaType
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
    private val searchDataSourceContainer: SearchDataSourceContainer
) : BaseRepository(), MovieRepository {

    override suspend fun getMovieGenreList(): List<Genre> {
        return wrap({ movieService.getGenreList() },
            { ListMapper(movieMappersContainer.genreMapper).mapList(it.genres) })
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

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDto? {
        return movieService.getMovieDetails(movieId).body()
    }

    override suspend fun getMovieCast(movieId: Int): CreditsDto? {
        return movieService.getMovieCast(movieId).body()
    }

    override suspend fun getSimilarMovie(movieId: Int): List<MovieDto>? {
        return movieService.getSimilarMovie(movieId).body()?.items
    }

    override suspend fun getMovieReviews(movieId: Int): List<ReviewsDto>? {
        return movieService.getMovieReviews(movieId).body()?.items
    }

    override suspend fun getMovieTrailer(movieId: Int): Trailer {
        return wrap({ movieService.getMovieTrailer(movieId) },
            { movieMappersContainer.trailerMapper.map(it) })
    }

    override suspend fun getRatedMovie(
        accountId: Int,
        sessionId: String,
    ): List<RatedMoviesDto>? {
        return movieService.getRatedMovie(accountId, sessionId).body()?.items
    }

    override suspend fun setRating(movieId: Int, value: Float, session_id: String): RatingDto? {
        return movieService.postRating(movieId, value, session_id).body()
    }

    /**
     * Actor.
     * */
    override suspend fun getTrendingActors(page: Int): List<Actor> {
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

    override fun searchForMovie(query: String): Flow<PagingData<Media>> {
        val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)
        val dataSource = searchDataSourceContainer.movieSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = { dataSource }).flow
    }

    override fun searchForSeries(query: String): Flow<PagingData<Media>> {
        val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)
        val dataSource = searchDataSourceContainer.seriesSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = { dataSource }).flow
    }

    //should remove empty list ...
    override fun searchForActor(query: String): Flow<PagingData<Media>> {
        val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)
        val dataSource = searchDataSourceContainer.actorSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = { dataSource }).flow
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

    override suspend fun getMediaData(type: AllMediaType, actorId: Int): Pager<Int, Media> {
        val pagingSourceFactory = when (type) {
            AllMediaType.ON_THE_AIR, AllMediaType.LATEST -> mediaDataSourceContainer.onTheAirTvShowDataSource
            AllMediaType.AIRING_TODAY -> mediaDataSourceContainer.airingTodayTvShowDataSource
            AllMediaType.POPULAR -> mediaDataSourceContainer.popularTvShowDataSource
            AllMediaType.TOP_RATED -> mediaDataSourceContainer.topRatedTvShowDataSource
            AllMediaType.TRENDING -> mediaDataSourceContainer.trendingMovieDataSource
            AllMediaType.NOW_STREAMING -> mediaDataSourceContainer.nowStreamingMovieMovieDataSource
            AllMediaType.UPCOMING -> mediaDataSourceContainer.upcomingMovieMovieDataSource
            AllMediaType.MYSTERY -> {
                val dataSource = mediaDataSourceContainer.movieGenreShowDataSource
                dataSource.setGenre(Constants.MYSTERY_ID, Constants.MOVIE_CATEGORIES_ID)
                dataSource
            }
            AllMediaType.ADVENTURE -> {
                val dataSource = mediaDataSourceContainer.movieGenreShowDataSource
                dataSource.setGenre(Constants.ADVENTURE_ID, Constants.MOVIE_CATEGORIES_ID)
                dataSource
            }
            AllMediaType.NON -> {
                val dataSource = mediaDataSourceContainer.actorMovieDataSource
                dataSource.setMovieActorID(actorId)
                dataSource
            }
        }
        return Pager(config = config, pagingSourceFactory = { pagingSourceFactory })
    }

    override suspend fun getActorData(): Pager<Int, Actor> {
        return Pager(config = config, pagingSourceFactory = { actorDataSource })
    }

    override fun getAllMedia(mediaType: Int): Flow<PagingData<Media>> {
        return Pager(
            config = config,
            pagingSourceFactory = {
                val dataSource = mediaDataSourceContainer.allMediaDataSource
                dataSource.setTypeMedia(mediaType)
                dataSource
            }).flow
    }

    override fun getMediaByGenre(genreID: Int, mediaType: Int): Flow<PagingData<Media>> {
        return Pager(
            config = config,
            pagingSourceFactory = {
                val dataSource = mediaDataSourceContainer.movieGenreShowDataSource
                dataSource.setGenre(genreID, mediaType)
                dataSource
            }
        ).flow
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