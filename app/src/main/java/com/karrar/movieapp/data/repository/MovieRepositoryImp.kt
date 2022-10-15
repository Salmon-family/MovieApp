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
    private val movieDao: MovieDao,
    private val mapperContainer: MapperContainer,

) : BaseRepository(), MovieRepository {

    override suspend fun getMovieGenreList(): List<Genre> {
        return wrap({ movieService.getGenreList() },
            { ListMapper(mapperContainer.genreMapper).mapList(it.genres) })
    }

    override suspend fun getAllMovies(): List<Media> {
        return wrap({ movieService.getAllMovies() },
            { ListMapper(mapperContainer.movieMapper).mapList(it.items) })
    }

    override suspend fun getPopularMovies(genres: List<Genre>): List<PopularMovie> {
        return wrap({ movieService.getPopularMovies() },
            { mapperContainer.popularMovieMapper.mapGenreMovie(it.items, genres) })
    }

    override suspend fun getTrendingMovies(page: Int): List<Media> {
        return wrap({ movieService.getTrendingMovies(page = page) },
            { ListMapper(mapperContainer.movieMapper).mapList(it.items) })
    }

    override suspend fun getDailyTrending(): List<Media> {
        return wrap({ movieService.getDailyTrending() },
            { ListMapper(mapperContainer.itemListMapper).mapList(it.items) })
    }

    override suspend fun getUpcomingMovies(page: Int): List<Media> {
        return wrap({ movieService.getUpcomingMovies(page = page) },
            { ListMapper(mapperContainer.movieMapper).mapList(it.items) })
    }

    override suspend fun getNowPlayingMovies(page: Int): List<Media> {
        return wrap({ movieService.getNowPlayingMovies(page = page) },
            { ListMapper(mapperContainer.movieMapper).mapList(it.items) })
    }

    override suspend fun getMovieListByGenreID(genreID: Int, page: Int): List<Media> {
        return wrap({ movieService.getMovieListByGenre(genreID = genreID, page = page) },
            { ListMapper(mapperContainer.movieMapper).mapList(it.items) })
    }

    /**
     * movie details
     * */

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return wrap({ movieService.getMovieDetails(movieId) }, { mapperContainer.movieDetailsMapper.map(it) })
    }

    override suspend fun getMovieCast(movieId: Int): List<Actor> {
        return wrap({ movieService.getMovieCast(movieId) }, { response ->
            ListMapper(mapperContainer.actorMapper).mapList(response.cast)
        })
    }

    override suspend fun getSimilarMovie(movieId: Int): List<Media> {
        return wrap({ movieService.getSimilarMovie(movieId) }, { response ->
            ListMapper(mapperContainer.movieMapper).mapList(response.items)
        })
    }

    override suspend fun getMovieReviews(movieId: Int): List<Review> {
        return wrap({ movieService.getMovieReviews(movieId) }, { response ->
            ListMapper(mapperContainer.reviewMapper).mapList(response.items)
        })
    }

    override suspend fun getMovieTrailer(movieId: Int): Trailer {
        return wrap({ movieService.getMovieTrailer(movieId) }, { mapperContainer.trailerMapper.map(it) })
    }

    override suspend fun getRatedMovie(
        accountId: Int,
        sessionId: String,
    ): List<RatedMovies> {
        return wrap({ movieService.getRatedMovie(accountId, sessionId) }, { response ->
            ListMapper(mapperContainer.ratedMoviesMapper).mapList(response.items)
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
            { ListMapper(mapperContainer.actorMapper).mapList(it.items) })
    }

    override suspend fun getActorDetails(actorId: Int): ActorDetails {
        return wrap({ movieService.getActorDetails(actorId) }, { mapperContainer.actorDetailsMapper.map(it) })
    }

    override suspend fun getActorMovies(actorId: Int): List<Media> {
        return wrap({ movieService.getActorMovies(actorId) },
            { ListMapper(mapperContainer.movieMapper).mapList(it.cast) })
    }

    /**
     * My list
     * */

    override suspend fun getAllLists(
        accountId: Int,
        sessionId: String,
    ): List<CreatedList> {
        return wrap({ movieService.getCreatedLists(accountId, sessionId) },
            { ListMapper(mapperContainer.createdListsMapper).mapList(it.items) })
    }

    override suspend fun getListDetails(listId: Int): MyListsDto {
        return wrap({ movieService.getList(listId) }, { it })
    }

    override suspend fun getSavedListDetails(listId: String): List<SaveListDetails> {
        return wrap({ movieService.getList(listId.toInt()) },
            { ListMapper(mapperContainer.saveListDetailsMapper).mapList(it.items) })
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
            ListMapper(mapperContainer.movieMapper).mapList(response.items)
        })
    }

    override suspend fun searchForSeries(query: String): List<Media> {
        return wrap({ movieService.searchForSeries(query) }, { response ->
            ListMapper(mapperContainer.seriesMapper).mapList(response.items)
        })
    }

    //should remove empty list ...
    override suspend fun searchForActor(query: String): List<Media> {
        return wrap({ movieService.searchForActor(query) }, { response ->
            response.items?.filter { it.knownForDepartment == Constants.ACTING }?.map {
                it.let { mapperContainer.searchActorMapper.map(it) }
            } ?: emptyList()
        })
    }

    override fun getAllSearchHistory(): Flow<List<SearchHistory>> {
        return movieDao.getAllSearchHistory().map { response ->
            response.map { mapperContainer.searchHistoryMapper.map(it) }
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

    override suspend fun getAllWatchedMovies(): List<WatchHistoryEntity> {
        return movieDao.getAllWatchedMovies()
    }
}