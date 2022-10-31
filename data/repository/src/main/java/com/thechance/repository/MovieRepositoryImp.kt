package com.thechance.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thechance.repository.local.AppConfiguration
import com.thechance.repository.local.database.daos.ActorDao
import com.thechance.repository.local.database.daos.MovieDao
import com.thechance.repository.local.database.entity.ActorEntity
import com.thechance.repository.local.database.entity.SearchHistoryEntity
import com.thechance.repository.local.database.entity.WatchHistoryEntity
import com.thechance.repository.local.database.entity.movie.*
import com.thechance.repository.mediaDataSource.ActorMovieDataSource
import com.thechance.repository.mediaDataSource.movie.MovieDataSourceContainer
import com.thechance.repository.remote.response.*
import com.thechance.repository.remote.response.actor.ActorDto
import com.thechance.repository.remote.response.actor.ActorMoviesDto
import com.thechance.repository.remote.response.genre.GenreDto
import com.thechance.repository.remote.response.movie.MovieDetailsDto
import com.thechance.repository.remote.response.movie.RatingDto
import com.thechance.repository.remote.response.review.ReviewsDto
import com.thechance.repository.remote.response.trailerVideosDto.TrailerDto
import com.thechance.repository.remote.service.MovieService
import com.thechance.repository.utilities.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val actorDao: ActorDao,
    private val appConfiguration: AppConfiguration,
    private val actorDataSource: ActorDataSource,
    private val mediaDataSourceContainer: MediaDataSourceContainer,
    private val movieMovieDataSource: MovieDataSourceContainer,
    private val actorMovieDataSource: ActorMovieDataSource,
) : BaseRepository(), MovieRepository {

    override suspend fun getMovieGenreList(): List<GenreDto>? {
        return movieService.getGenreList().body()?.genres
    }

    override suspend fun getDailyTrending(): BaseListResponse<DailyTrendingDto> {
        return movieService.getDailyTrending().body()!!
    }

    /**
     * movie details
     * */

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDto? {
        return movieService.getMovieDetails(movieId).body()
    }

    override suspend fun getMovieCast(movieId: Int): List<ActorDto>? {
        return movieService.getMovieCast(movieId).body()?.cast
    }

    override suspend fun getSimilarMovie(movieId: Int): List<MovieDto>? {
        return movieService.getSimilarMovie(movieId).body()?.items
    }

    override suspend fun getMovieReviews(movieId: Int): List<ReviewsDto>? {
        return movieService.getMovieReviews(movieId).body()?.items
    }

    override suspend fun getMovieTrailer(movieId: Int): TrailerDto? {
        return movieService.getMovieTrailer(movieId).body()
    }

    override suspend fun getRatedMovie(
        accountId: Int,
        sessionId: String,
    ): List<RatedMoviesDto>? {
        return movieService.getRatedMovie(accountId, sessionId).body()?.items
    }

    override suspend fun setRating(movieId: Int, value: Float, session_id: String): RatingDto {
        return wrap({ movieService.postRating(movieId, value, session_id) }, { it })
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
    ): AddMovieDto {
        return wrap({ movieService.addMovieToList(listId, sessionId, movieId) }, { it })
    }


    /**
     * searching
     * */

//    override fun searchForMovie(query: String): Flow<PagingData<Media>> {
//        val dataSource = searchDataSourceContainer.movieSearchDataSource
//        dataSource.setSearchText(query)
//        return Pager(config = config, pagingSourceFactory = { dataSource }).flow
//    }
//
//    override fun searchForSeries(query: String): Flow<PagingData<Media>> {
//        val dataSource = searchDataSourceContainer.seriesSearchDataSource
//        dataSource.setSearchText(query)
//        return Pager(config = config, pagingSourceFactory = { dataSource }).flow
//    }
//
//    override fun searchForActor(query: String): Flow<PagingData<Media>> {
//        val dataSource = searchDataSourceContainer.actorSearchDataSource
//        dataSource.setSearchText(query)
//        return Pager(config = config, pagingSourceFactory = { dataSource }).flow
//    }
//
//    override fun getAllSearchHistory(): Flow<List<SearchHistory>> {
//        return movieDao.getAllSearchHistory().map { response ->
//            response.map { movieMappersContainer.searchHistoryMapper.map(it) }
//        }
//    }

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

    override fun getPopularMovies(): Flow<List<PopularMovieEntity>> {
        return movieDao.getPopularMovies()
    }

    override fun getTrendingMovies(): Flow<List<TrendingMovieEntity>> {
        return movieDao.getTrendingMovies()
    }

    override fun getNowStreamingMovies(): Flow<List<NowStreamingMovieEntity>> {
        return movieDao.getNowStreamingMovies()
    }

    override fun getAdventureMovies(): Flow<List<AdventureMovieEntity>> {
        return movieDao.getAdventureMovies()
    }

    override fun getMysteryMovies(): Flow<List<MysteryMovieEntity>> {
        return movieDao.getMysteryMovies()
    }

    override suspend fun getMysteryMovies(page: Int): List<MovieDto> {
        return movieService.getMovieListByGenre(genreID = Constants.MYSTERY_ID).body()?.items
            ?: emptyList()
    }

    override suspend fun insertMysteryMovies(items: List<MysteryMovieEntity>) {
        movieDao.insertMysteryMovie(items)
    }

    override suspend fun deleteMysteryMovies() {
        movieDao.deleteAllMysteryMovies()
    }

    override fun getTrendingActors(): Flow<List<ActorEntity>> {
        return actorDao.getActors()
    }

    override suspend fun getTrendingActors(page: Int): List<ActorDto> {
        return movieService.getTrendingActors().body()?.items ?: emptyList()
    }

    override suspend fun insertTrendingActors(items: List<ActorEntity>) {
        actorDao.insertActors(items)
    }

    override suspend fun deleteTrendingActors() {
        actorDao.deleteActors()
    }

    override suspend fun getPopularMovies(page: Int): List<MovieDto> {
        return movieService.getPopularMovies().body()?.items ?: emptyList()

    }

    override suspend fun getTrendingMovies(page: Int): List<MovieDto> {
        return movieService.getTrendingMovies(page = page).body()?.items ?: emptyList()
    }

    override suspend fun insertTrendingMovies(items: List<TrendingMovieEntity>) {
        movieDao.insertTrendingMovie(items)
    }

    override suspend fun deleteTrendingMovies() {
        movieDao.deleteAllTrendingMovies()
    }

    override suspend fun getNowStreamingMovies(page: Int): List<MovieDto> {
        return movieService.getNowPlayingMovies().body()?.items ?: emptyList()
    }

    override suspend fun insertNowStreamingMovies(items: List<NowStreamingMovieEntity>) {
        movieDao.insertNowStreamingMovie(items)
    }

    override suspend fun deleteNowStreamingMovies() {
        movieDao.deleteAllNowStreamingMovies()
    }

    override fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>> {
        return movieDao.getUpcomingMovies()
    }

    override suspend fun getUpcomingMovies(page: Int): List<MovieDto> {
        return movieService.getUpcomingMovies().body()?.items ?: emptyList()
    }

    override suspend fun insertUpcomingMovies(items: List<UpcomingMovieEntity>) {
        movieDao.insertUpcomingMovie(items)
    }

    override suspend fun deleteUpcomingMovies() {
        movieDao.deleteAllUpcomingMovies()
    }

    override suspend fun getAdventureMovies(page: Int): List<MovieDto> {
        return movieService.getMovieListByGenre(genreID = Constants.ADVENTURE_ID).body()?.items
            ?: emptyList()
    }

    override suspend fun insertAdventureMovies(items: List<AdventureMovieEntity>) {
        movieDao.insertAdventureMovie(items)
    }

    override suspend fun deleteAdventureMovies() {
        movieDao.deleteAllAdventureMovies()
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

    override suspend fun insertPopularMovies(items: List<PopularMovieEntity>) {
        movieDao.insertPopularMovies(items)
    }

    override suspend fun deletePopularMovies() {
        movieDao.deletePopularMovies()
    }

    override suspend fun saveRequestDate(value: Long) {
        appConfiguration.saveRequestDate(value)
    }

    override suspend fun getRequestDate(): Long? {
        return appConfiguration.getRequestDate()
    }

}