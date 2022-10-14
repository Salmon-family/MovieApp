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
    private val actorDetailsMapper: ActorDetailsMapper,
    private val actorMapper: ActorMapper,
    private val genreMapper: GenreMapper,
    private val movieMapper: MovieMapper,
    private val tvShowsMapper: TVShowMapper,
    private val searchActorMapper: SearchActorMapper,
    private val seriesMapper: SearchSeriesMapper,
    private val movieDao: MovieDao,
    private val searchHistoryMapper: SearchHistoryMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val reviewMapper: ReviewMapper,
    private val trailerMapper: TrailerMapper,
    private val popularMovieMapper: PopularMovieMapper,
    private val ratedMoviesMapper: RatedMoviesMapper,
    private val createdListsMapper: CreatedListsMapper,
    private val saveListDetailsMapper: SaveListDetailsMapper
) : BaseRepository(), MovieRepository {

    override suspend fun getPopularMovies2(genres: List<Genre>): List<PopularMovie> {
        return wrap2({ movieService.getPopularMovies() },
            { popularMovieMapper.mapGenreMovie(it.items, genres) })
    }

    override suspend fun getMovieGenreList2(): List<Genre> {
        return wrap2({ movieService.getGenreList() },
            { ListMapper(genreMapper).mapList(it.genres) })
    }

    override suspend fun searchForActor(query: String): List<Media> {
        return wrap2({ movieService.searchForActor(query) }, { response ->
            response.items?.filter { it.knownForDepartment == Constants.ACTING }?.map {
                it.let { searchActorMapper.map(it) }
            } ?: emptyList()
        })
    }

    override suspend fun searchForMovie(query: String): List<Media> {
        return wrap2({ movieService.searchForMovie(query) }, { response ->
            ListMapper(movieMapper).mapList(response.items)
        })
    }

    override suspend fun searchForSeries(query: String): List<Media> {
        return wrap2({ movieService.searchForSeries(query) }, { response ->
            ListMapper(seriesMapper).mapList(response.items)
        })
    }

    override fun getAllSearchHistory(): Flow<List<SearchHistory>> {
        return movieDao.getAllSearchHistory().map { response ->
            response.map { searchHistoryMapper.map(it) }
        }
    }

    override suspend fun getDailyTrending(): List<Media> {
        return wrap2({ movieService.getDailyTrending() }, { response ->
            ListMapper(tvShowsMapper).mapList(response.items)
        })
    }

    override suspend fun getAllMovies(): List<Media> {
        return wrap2({ movieService.getAllMovies() }, {
            ListMapper(movieMapper).mapList(it.items)
        })
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return wrap2({ movieService.getMovieDetails(movieId) }, { movieDetailsMapper.map(it) })
    }

    override suspend fun getMovieCast(movieId: Int): List<Actor> {
        return wrap2({ movieService.getMovieCast(movieId) }, { response ->
            ListMapper(actorMapper).mapList(response.cast)
        })
    }

    override suspend fun getSimilarMovie(movieId: Int): List<Media> {
        return wrap2({ movieService.getSimilarMovie(movieId) }, { response ->
            ListMapper(movieMapper).mapList(response.items)
        })
    }

    override suspend fun getMovieReviews(movieId: Int): List<Review> {
        return wrap2({ movieService.getMovieReviews(movieId) }, { response ->
            ListMapper(reviewMapper).mapList(response.items)
        })
    }

    override suspend fun setRating(movieId: Int, value: Float, session_id: String): RatingDto {
        return wrap2({ movieService.postRating(movieId, value, session_id) }, { it })
    }

    override suspend fun getMovieTrailer(movieId: Int): Trailer {
        return wrap2({ movieService.getMovieTrailer(movieId) }, { trailerMapper.map(it) })
    }

    override suspend fun getAllLists(
        accountId: Int,
        sessionId: String,
    ): List<CreatedList> {
        return wrap2({ movieService.getCreatedLists(accountId, sessionId) }, {
            ListMapper(createdListsMapper).mapList(it.items)
        })
    }

    override suspend fun addMovieToList(
        sessionId: String,
        listId: Int,
        movieId: Int,
    ): AddMovieDto {
        return wrap2({ movieService.addMovieToList(listId, sessionId, movieId)  }, { it })
    }

    override suspend fun getListDetails(listId: Int): MyListsDto {
        return wrap2({ movieService.getList(listId) }, { it })
    }

    override suspend fun getRatedMovie(
        accountId: Int,
        sessionId: String,
    ): List<RatedMovies> {
        return wrap2({ movieService.getRatedMovie(accountId, sessionId) }, { response ->
            ListMapper(ratedMoviesMapper).mapList(response.items)
        })
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

    override suspend fun clearWatchHistory() {
        return movieDao.deleteAllWatchedMovies()
    }

    override suspend fun createList(
        sessionId: String,
        name: String,
    ): AddListResponse {
        return wrap2({ movieService.createList(sessionId,name) }, { it })
    }

    override suspend fun getTrendingMovies2(page: Int): List<Media> {
        return wrap2({ movieService.getTrendingMovies(page = page) },
            { ListMapper(movieMapper).mapList(it.items) })
    }

    override suspend fun getTrendingActors(): List<Actor> {
        return wrap2({ movieService.getTrendingActors() },
            { ListMapper(actorMapper).mapList(it.items) })
    }

    override suspend fun getUpcomingMovies2(page: Int): List<Media> {
        return wrap2({ movieService.getUpcomingMovies(page = page) },
            { ListMapper(movieMapper).mapList(it.items) })
    }

    override suspend fun getNowPlayingMovies2(page: Int): List<Media> {
        return wrap2({ movieService.getNowPlayingMovies(page = page) },
            { ListMapper(movieMapper).mapList(it.items) })
    }

    override suspend fun getMovieListByGenreID2(genreID: Int, page: Int): List<Media> {
        return wrap2({ movieService.getMovieListByGenre(genreID, page = page) },
            { ListMapper(movieMapper).mapList(it.items) })
    }


    override suspend fun getActorDetails(actorId: Int): ActorDetails {
        return wrap2({ movieService.getActorDetails(actorId) }, { actorDetailsMapper.map(it) })
    }

    override suspend fun getActorMovies(actorId: Int): List<Media> {
        return wrap2({ movieService.getActorMovies(actorId) },
            { ListMapper(movieMapper).mapList(it.cast?.mapNotNull { cast -> cast }) })
    }

    override suspend fun getActorMovies2(actorId: Int): List<Media> {
        return wrap2({ movieService.getActorMovies(actorId) },
            { ListMapper(movieMapper).mapList(it.cast) })
    }

    override suspend fun getSavedListDetails(listId: String): List<SaveListDetails> {
        return wrap2(
            { movieService.getList(listId.toInt()) },
            { ListMapper(saveListDetailsMapper).mapList(it.items) })
    }
}