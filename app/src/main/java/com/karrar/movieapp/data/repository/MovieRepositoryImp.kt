package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.response.AddListResponse
import com.karrar.movieapp.data.remote.response.AddMovieDto
import com.karrar.movieapp.data.remote.response.MyListsDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.*
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.utilities.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val genreMapper: GenreMapper,
    private val movieDao: MovieDao,

    private val movieMapper: MovieMapper,
    private val popularMovieMapper: PopularMovieMapper,

    private val movieDetailsMapper: MovieDetailsMapper,
    private val reviewMapper: ReviewMapper,
    private val trailerMapper: TrailerMapper,
    private val ratedMoviesMapper: RatedMoviesMapper,

    private val actorDetailsMapper: ActorDetailsMapper,
    private val actorMapper: ActorMapper,
    private val tvShowsMapper: TVShowMapper,

    private val searchActorMapper: SearchActorMapper,
    private val seriesMapper: SearchSeriesMapper,
    private val searchHistoryMapper: SearchHistoryMapper,

    private val createdListsMapper: CreatedListsMapper,
    private val saveListDetailsMapper: SaveListDetailsMapper
) : BaseRepository(), MovieRepository {

    override suspend fun getMovieGenreList(): List<Genre> {
        return wrap({ movieService.getGenreList() },
            { ListMapper(genreMapper).mapList(it.genres) })
    }

    override suspend fun getAllMovies(): List<Media> {
        return wrap({ movieService.getAllMovies() },
            { ListMapper(movieMapper).mapList(it.items) })
    }

    override suspend fun getPopularMovies(genres: List<Genre>): List<PopularMovie> {
        return wrap({ movieService.getPopularMovies() },
            { popularMovieMapper.mapGenreMovie(it.items, genres) })
    }

    override suspend fun getTrendingMovies(page: Int): List<Media> {
        return wrap({ movieService.getTrendingMovies(page = page) },
            { ListMapper(movieMapper).mapList(it.items) })
    }

    override suspend fun getDailyTrending(): List<Media> {
        return wrap({ movieService.getDailyTrending() },
            { ListMapper(tvShowsMapper).mapList(it.items) })
    }

    override suspend fun getUpcomingMovies(page: Int): List<Media> {
        return wrap({ movieService.getUpcomingMovies(page = page) },
            { ListMapper(movieMapper).mapList(it.items) })
    }

    override suspend fun getNowPlayingMovies(page: Int): List<Media> {
        return wrap({ movieService.getNowPlayingMovies(page = page) },
            { ListMapper(movieMapper).mapList(it.items) })
    }

    override suspend fun getMovieListByGenreID(genreID: Int, page: Int): List<Media> {
        return wrap({ movieService.getMovieListByGenre(genreID = genreID, page = page) },
            { ListMapper(movieMapper).mapList(it.items) })
    }

    /**
     * movie details
     * */

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return wrap({ movieService.getMovieDetails(movieId) }, { movieDetailsMapper.map(it) })
    }

    override suspend fun getMovieCast(movieId: Int): List<Actor> {
        return wrap({ movieService.getMovieCast(movieId) }, { response ->
            ListMapper(actorMapper).mapList(response.cast)
        })
    }

    override suspend fun getSimilarMovie(movieId: Int): List<Media> {
        return wrap({ movieService.getSimilarMovie(movieId) }, { response ->
            ListMapper(movieMapper).mapList(response.items)
        })
    }

    override suspend fun getMovieReviews(movieId: Int): List<Review> {
        return wrap({ movieService.getMovieReviews(movieId) }, { response ->
            ListMapper(reviewMapper).mapList(response.items)
        })
    }

    override suspend fun getMovieTrailer(movieId: Int): Trailer {
        return wrap({ movieService.getMovieTrailer(movieId) }, { trailerMapper.map(it) })
    }

    override suspend fun getRatedMovie(
        accountId: Int,
        sessionId: String,
    ): List<RatedMovies> {
        return wrap({ movieService.getRatedMovie(accountId, sessionId) }, { response ->
            ListMapper(ratedMoviesMapper).mapList(response.items)
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
            { ListMapper(actorMapper).mapList(it.items) })
    }

    override suspend fun getActorDetails(actorId: Int): ActorDetails {
        return wrap({ movieService.getActorDetails(actorId) }, { actorDetailsMapper.map(it) })
    }

    override suspend fun getActorMovies(actorId: Int): List<Media> {
        return wrap({ movieService.getActorMovies(actorId) },
            { ListMapper(movieMapper).mapList(it.cast) })
    }

    /**
     * My list
     * */

    override suspend fun getAllLists(
        accountId: Int,
        sessionId: String,
    ): List<CreatedList> {
        return wrap({ movieService.getCreatedLists(accountId, sessionId) },
            { ListMapper(createdListsMapper).mapList(it.items) })
    }

    override suspend fun getListDetails(listId: Int): MyListsDto {
        return wrap({ movieService.getList(listId) }, { it })
    }

    override suspend fun getSavedListDetails(listId: String): List<SaveListDetails> {
        return wrap({ movieService.getList(listId.toInt()) },
            { ListMapper(saveListDetailsMapper).mapList(it.items) })
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

    override suspend fun searchForMovie(query: String): List<Media> {
        return wrap({ movieService.searchForMovie(query) }, { response ->
            ListMapper(movieMapper).mapList(response.items)
        })
    }

    override suspend fun searchForSeries(query: String): List<Media> {
        return wrap({ movieService.searchForSeries(query) }, { response ->
            ListMapper(seriesMapper).mapList(response.items)
        })
    }

    //should remove empty list ...
    override suspend fun searchForActor(query: String): List<Media> {
        return wrap({ movieService.searchForActor(query) }, { response ->
            response.items?.filter { it.knownForDepartment == Constants.ACTING }?.map {
                it.let { searchActorMapper.map(it) }
            } ?: emptyList()
        })
    }

    override fun getAllSearchHistory(): Flow<List<SearchHistory>> {
        return movieDao.getAllSearchHistory().map { response ->
            response.map { searchHistoryMapper.map(it) }
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