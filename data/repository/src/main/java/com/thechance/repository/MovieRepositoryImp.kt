package com.thechance.repository

import androidx.paging.Pager
import com.thechance.local.database.daos.ActorDao
import com.thechance.local.database.daos.MovieDao
import com.thechance.local.database.entity.ActorEntity
import com.thechance.local.database.entity.SearchHistoryEntity
import com.thechance.local.database.entity.WatchHistoryEntity
import com.thechance.local.database.entity.movie.*
import com.thechance.remote.response.*
import com.thechance.remote.response.actor.ActorDto
import com.thechance.remote.response.actor.ActorMoviesDto
import com.thechance.remote.response.genre.GenreDto
import com.thechance.remote.response.movie.MovieDetailsDto
import com.thechance.remote.response.movie.RatingDto
import com.thechance.remote.response.review.ReviewsDto
import com.thechance.remote.response.trailerVideosDto.TrailerDto
import com.thechance.remote.service.MovieService
import com.thechance.repository.configuration.AppConfiguration
import com.thechance.repository.mapper.movie.LocalMovieMappersContainer
import com.thechance.repository.mediaDataSource.ActorMovieDataSource
import com.thechance.repository.mediaDataSource.movie.MovieDataSourceContainer
import com.thechance.repository.serchDataSource.SearchDataSourceContainer
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val actorDao: ActorDao,
    private val appConfiguration: AppConfiguration,
    private val actorDataSource: ActorDataSource,
    private val dataMappers: LocalMovieMappersContainer,
    private val mediaDataSourceContainer: MediaDataSourceContainer,
    private val searchDataSourceContainer: SearchDataSourceContainer,
    private val movieMovieDataSource: MovieDataSourceContainer,
    private val actorMovieDataSource: ActorMovieDataSource
) : BaseRepository(), MovieRepository {

    override suspend fun getMovieGenreList(): List<GenreDto>? {
        return movieService.getGenreList().body()?.genres
    }


    override suspend fun getDailyTrending(): BaseListResponse<DailyTrendingDto> {
        return movieService.getDailyTrending().body()!!
    }


    override suspend fun getRatedMovie(): List<RatedMoviesDto>? {
        return movieService.getRatedMovie().body()?.items
    }

    override suspend fun setRating(movieId: Int, value: Float): RatingDto? {
        return movieService.postRating(movieId, value).body()
    }

    /**
     * Actor.
     * */

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
    ): AddMovieDto? {
        return movieService.addMovieToList(listId, sessionId, movieId).body()
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

    override suspend fun getPopularMovies(): Flow<List<PopularMovieEntity>> {
        refreshOneTimePerDay(
            appConfiguration.getRequestDate(Constants.POPULAR_MOVIE_REQUEST_DATE_KEY),
            ::refreshPopularMovies
        )
        return movieDao.getPopularMovies()
    }

    override suspend fun getTrendingMovies(): Flow<List<TrendingMovieEntity>> {
        refreshOneTimePerDay(
            appConfiguration.getRequestDate(Constants.TRENDING_MOVIE_REQUEST_DATE_KEY),
            ::refreshTrendingMovies
        )
        return movieDao.getTrendingMovies()
    }

    override suspend fun getNowStreamingMovies(): Flow<List<NowStreamingMovieEntity>> {
        refreshOneTimePerDay(
            appConfiguration.getRequestDate(Constants.NOW_STREAMING_MOVIE_REQUEST_DATE_KEY),
            ::refreshNowPlayingMovies
        )
        return movieDao.getNowStreamingMovies()
    }

    override suspend fun getAdventureMovies(): Flow<List<AdventureMovieEntity>> {
        refreshOneTimePerDay(
            appConfiguration.getRequestDate(Constants.ADVENTURE_MOVIE_REQUEST_DATE_KEY),
            ::refreshAdventureMovies
        )
        return movieDao.getAdventureMovies()
    }

    override suspend fun getMysteryMovies(): Flow<List<MysteryMovieEntity>> {
        refreshOneTimePerDay(
            appConfiguration.getRequestDate(Constants.MYSTERY_MOVIE_REQUEST_DATE_KEY),
            ::refreshMysteryMovies
        )
        return movieDao.getMysteryMovies()
    }

    override suspend fun getTrendingActors(): Flow<List<ActorEntity>> {
        refreshOneTimePerDay(
            appConfiguration.getRequestDate(Constants.ACTOR_REQUEST_DATE_KEY),
            ::refreshTrendingActors
        )
        return actorDao.getActors()
    }


    override suspend fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>> {
        refreshOneTimePerDay(
            appConfiguration.getRequestDate(Constants.UPCOMING_MOVIE_REQUEST_DATE_KEY),
            ::refreshUpcomingMovies
        )
        return movieDao.getUpcomingMovies()
    }


    override suspend fun getTrendingMoviesPager(): Pager<Int, MovieDto> {
        return Pager(config = config,
            pagingSourceFactory = { movieMovieDataSource.trendingMovieDataSource })
    }

    override suspend fun getNowPlayingMoviesPager(): Pager<Int, MovieDto> {
        return Pager(config = config,
            pagingSourceFactory = { movieMovieDataSource.nowStreamingMovieMovieDataSource })
    }

    override suspend fun getUpcomingMoviesPager(): Pager<Int, MovieDto> {
        return Pager(config = config,
            pagingSourceFactory = { movieMovieDataSource.upcomingMovieMovieDataSource })
    }

    override suspend fun getAdventureMoviesPager(): Pager<Int, MovieDto> {
        val dataSource = movieMovieDataSource.movieByGenreDataSource
        dataSource.setGenre(Constants.MYSTERY_ID)
        return Pager(config = config, pagingSourceFactory = { dataSource })
    }

    override suspend fun getMysteryMoviesPager(): Pager<Int, MovieDto> {
        val dataSource = movieMovieDataSource.movieByGenreDataSource
        dataSource.setGenre(Constants.ADVENTURE_ID)
        return Pager(config = config, pagingSourceFactory = { dataSource })
    }

    override suspend fun getActorMoviesPager(actorId: Int): Pager<Int, MovieDto> {
        val dataSource = actorMovieDataSource
        dataSource.setMovieActorID(actorId)
        return Pager(config = config, pagingSourceFactory = { dataSource })
    }


    private suspend fun refreshPopularMovies(currentDate: Date) {
        val genres = getMovieGenreList() ?: emptyList()
        refreshWrapper(
            { movieService.getPopularMovies() },
            { items ->
                items?.map { dataMappers.popularMovieMapper.map(it, genres) }
            },
            {
                movieDao.deletePopularMovies()
                movieDao.insertPopularMovies(it)
                appConfiguration.saveRequestDate(
                    Constants.POPULAR_MOVIE_REQUEST_DATE_KEY,
                    currentDate.time
                )
            },
        )
    }

    private suspend fun refreshTrendingMovies(currentDate: Date) {
        refreshWrapper(
            { movieService.getTrendingMovies() },
            { list ->
                list?.map { dataMappers.trendingMovieMapper.map(it) }
            },
            {
                movieDao.deleteAllTrendingMovies()
                movieDao.insertTrendingMovie(it)
                appConfiguration.saveRequestDate(
                    Constants.TRENDING_MOVIE_REQUEST_DATE_KEY,
                    currentDate.time
                )
            },
        )
    }

    private suspend fun refreshNowPlayingMovies(currentDate: Date) {
        refreshWrapper(
            { movieService.getNowPlayingMovies() },
            { list ->
                list?.map { dataMappers.nowStreamingMovieMapper.map(it) }
            },
            {
                movieDao.deleteAllNowStreamingMovies()
                movieDao.insertNowStreamingMovie(it)
                appConfiguration.saveRequestDate(
                    Constants.NOW_STREAMING_MOVIE_REQUEST_DATE_KEY,
                    currentDate.time
                )
            },
        )
    }

    private suspend fun refreshUpcomingMovies(currentDate: Date) {
        refreshWrapper(
            { movieService.getUpcomingMovies() },
            { list ->
                list?.map { dataMappers.upcomingMovieMapper.map(it) }
            },
            {
                movieDao.deleteAllUpcomingMovies()
                movieDao.insertUpcomingMovie(it)
                appConfiguration.saveRequestDate(
                    Constants.UPCOMING_MOVIE_REQUEST_DATE_KEY,
                    currentDate.time
                )
            },
        )
    }

    private suspend fun refreshAdventureMovies(currentDate: Date) {
        refreshWrapper(
            { movieService.getMovieListByGenre(genreID = Constants.ADVENTURE_ID) },
            { list ->
                list?.map { dataMappers.adventureMovieMapper.map(it) }
            },
            {
                movieDao.deleteAllAdventureMovies()
                movieDao.insertAdventureMovie(it)
                appConfiguration.saveRequestDate(
                    Constants.ADVENTURE_MOVIE_REQUEST_DATE_KEY,
                    currentDate.time
                )
            },
        )
    }

    private suspend fun refreshMysteryMovies(currentDate: Date) {
        refreshWrapper(
            { movieService.getMovieListByGenre(genreID = Constants.MYSTERY_ID) },
            { list ->
                list?.map { dataMappers.mysteryMovieMapper.map(it) }
            },
            {
                movieDao.deleteAllMysteryMovies()
                movieDao.insertMysteryMovie(it)
                appConfiguration.saveRequestDate(
                    Constants.MYSTERY_MOVIE_REQUEST_DATE_KEY,
                    currentDate.time
                )
            },
        )
    }

    private suspend fun refreshTrendingActors(currentDate: Date) {
        refreshWrapper(
            { movieService.getTrendingActors() }, { items ->
                items?.map { dataMappers.actorMapper.map(it) }
            }, {
                actorDao.deleteActors()
                actorDao.insertActors(it)
                appConfiguration.saveRequestDate(Constants.ACTOR_REQUEST_DATE_KEY, currentDate.time)
            }
        )
    }


    /**
     * searching
     * */

    override suspend fun searchForMoviePager(query: String): Pager<Int, MovieDto> {
        val dataSource = searchDataSourceContainer.movieSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = { dataSource })
    }

    override suspend fun searchForActorPager(query: String): Pager<Int, ActorDto> {
        val dataSource = searchDataSourceContainer.actorSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = { dataSource })
    }


    override suspend fun getAllSearchHistory(): Flow<List<SearchHistoryEntity>> {
        return movieDao.getAllSearchHistory()
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

    override suspend fun deleteRating(movieId: Int): RatingDto? {
        return movieService.deleteRating(movieId).body()
    }

    override suspend fun getMovieTrailer(movieId: Int): TrailerDto? {
        return movieService.getMovieTrailer(movieId).body()
    }

}