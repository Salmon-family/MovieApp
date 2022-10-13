package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.State
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

    override fun getUpcomingMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getUpcomingMovies() }, { baseResponse ->
            baseResponse.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTopRatedMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getTopRatedMovies() }, { baseResponse ->
            baseResponse.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun searchForActor(query: String): List<Media> {
        return wrap2({ movieService.searchForActor(query) }, { response ->
            response.items?.filter { it.knownForDepartment == Constants.ACTING }?.map {
                it.let { searchActorMapper.map(it) }
            } ?: emptyList()
        })
    }

    override fun getNowPlayingMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getNowPlayingMovies() }, { response ->
            response.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun searchForMovie(query: String): List<Media> {
        return wrap2({ movieService.searchForMovie(query) }, { response ->
            response.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun searchForSeries(query: String): List<Media> {
        return wrap2({ movieService.searchForSeries(query) }, { response ->
            response.items?.map { seriesMapper.map(it) } ?: emptyList()
        })
    }

    override fun getAllSearchHistory(): Flow<List<SearchHistory>> {
        return movieDao.getAllSearchHistory().map { response ->
            response.map { searchHistoryMapper.map(it) }
        }
    }

    override fun getTrendingMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getTrendingMovies() }, { response ->
            response.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getMovieGenreList(): Flow<State<List<Genre>>> {
        return wrap({ movieService.getGenreList() }, { response ->
            response.genres?.map { genreMapper.map(it) } ?: emptyList()
        })
    }

    override fun getMovieListByGenreID(genreID: Int): Flow<State<List<Media>>> {
        return wrap({ movieService.getMovieListByGenre(genreID) }, { response ->
            response.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getDailyTrending(): List<Media> {
        return wrap2({ movieService.getDailyTrending() }, { response ->
            response.items?.map { tvShowsMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getAllMovies(): List<Media> {
        return wrap2({ movieService.getAllMovies() }, {
            ListMapper(movieMapper).mapList(it.items) })
    }


    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return wrap2({ movieService.getMovieDetails(movieId) },
            {
                movieDetailsMapper.map(it)
            }
        )
    }

    override suspend fun getMovieCast(movieId: Int): List<Actor> {
        return wrap2({ movieService.getMovieCast(movieId) },
            { response ->
                response.cast?.map { actorMapper.map(it) }
            }) ?: emptyList()
    }

    override suspend fun getSimilarMovie(movieId: Int): List<Media> {
        return wrap2({ movieService.getSimilarMovie(movieId) }, { response ->
            response.items?.map { movieMapper.map(it) }
        }) ?: emptyList()
    }

    override suspend fun getMovieReviews(movieId: Int): List<Review> {
        return wrap2({ movieService.getMovieReviews(movieId) }, { response ->
            response.items?.map { reviewMapper.map(it) }
        }) ?: emptyList()
    }

    override fun setRating(movieId: Int, value: Float, session_id: String): Flow<State<RatingDto>> {
        return wrapWithFlow { movieService.postRating(movieId, value, session_id) }
    }

    override suspend fun getMovieTrailer(movieId: Int): Flow<State<Trailer>> {
        return wrap({ movieService.getMovieTrailer(movieId) }, {
            trailerMapper.map(it)
        })
    }

    override suspend fun getAllLists(
        accountId: Int,
        sessionId: String,
    ): List<CreatedList> {
        return wrap2({ movieService.getCreatedLists(accountId, sessionId) }, {
              ListMapper(createdListsMapper).mapList(it.items)})
        }



    override fun addMovieToList(
        sessionId: String,
        listId: Int,
        movieId: Int,
    ): Flow<State<AddMovieDto>> {
        return wrapWithFlow { movieService.addMovieToList(listId, sessionId, movieId) }
    }

    override fun getListDetails(listId: Int): Flow<State<MyListsDto>> {
        return wrapWithFlow { movieService.getList(listId) }
    }

    override suspend fun getRatedMovie(
        accountId: Int,
        sessionId: String,
    ): List<RatedMovies> {
        return wrap2({ movieService.getRatedMovie(accountId, sessionId) }, { baseResponse ->
            baseResponse.items?.map { ratedMoviesMapper.map(it) } ?: emptyList()
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

    override fun createList(sessionId: String, name: String): Flow<State<AddListResponse>> {
        return wrapWithFlow {
            movieService.createList(sessionId, name)
        }
    }

    override suspend fun getTrendingMovies2(): List<Media> {
        return wrap2({ movieService.getTrendingMovies() },
            { ListMapper(movieMapper).mapList(it.items) })
    }

    override suspend fun getTrendingActors(): List<Actor> {
        return wrap2({ movieService.getTrendingActors() },
            { ListMapper(actorMapper).mapList(it.items) })
    }

    override suspend fun getUpcomingMovies2(): List<Media> {
        return wrap2({ movieService.getUpcomingMovies() },
            { ListMapper(movieMapper).mapList(it.items) })
    }

    override suspend fun getNowPlayingMovies2(): List<Media> {
        return wrap2({ movieService.getNowPlayingMovies() },
            { ListMapper(movieMapper).mapList(it.items) })
    }

    override suspend fun getMovieListByGenreID2(genreID: Int): List<Media> {
        return wrap2({ movieService.getMovieListByGenre(genreID) },
            { ListMapper(movieMapper).mapList(it.items) })
    }


    override suspend fun getActorDetails(actorId: Int): ActorDetails {
        return wrap2({ movieService.getActorDetails(actorId) }, { actorDetailsMapper.map(it) })
    }

    override suspend fun getActorMovies(actorId: Int): List<Media> {
        return wrap2({ movieService.getActorMovies(actorId) },
            {
                ListMapper(movieMapper).mapList(it.cast?.mapNotNull { cast ->
                    cast
                })
            })
    }

    override suspend fun getSavedListDetails(listId: String): List<SaveListDetails> {
        return wrap2(
            { movieService.getList(listId.toInt()) },
            { ListMapper(saveListDetailsMapper).mapList(it.items) })

    }
}