package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.ActorDetailsMapper
import com.karrar.movieapp.domain.mappers.ActorMoviesMapper
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.domain.mappers.ActorMapper
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.MediaMapper
import com.karrar.movieapp.domain.mappers.PopularMovieMapper
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.PopularMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val actorDetailsMapper: ActorDetailsMapper,
    private val actorMoviesMapper: ActorMoviesMapper,
    private val actorMapper: ActorMapper,
    private val genreMapper: GenreMapper,
    private val movieMapper: MediaMapper
) : BaseRepository(),MovieRepository {

    override fun getPopularMovies(): Flow<State<List<PopularMovie>>> {
        val mapper = PopularMovieMapper()
        return flow {
            emit(State.Loading)
            try {
                val responseGenre = movieService.getGenreList()
                val genreList = responseGenre.body()?.genres?.map { genreMapper.map(it) }
                val responseMovie = movieService.getPopularMovies()
                val items = responseMovie.body()?.items?.map { mapper.map(it) }
                val movieWithGenre = mapper.mapGenreMovie(items!!, genreList!!)
                emit(State.Success(movieWithGenre))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
    }

    override fun getTrendingActors(): Flow<State<List<Actor>>> {
        return wrap({ movieService.getTrendingActors() }) { it ->
            it.items?.map { actorMapper.map(it) } ?: emptyList()
        }
    }

    override fun getActorDetails(actorId: Int): Flow<State<ActorDetails>> {
        return wrap({ movieService.getActorDetails(actorId) }, { actorDetailsMapper.map(it) })
    }

    override fun getActorMovies(actorId: Int): Flow<State<List<Media>>> {
        return wrap({ movieService.getActorMovies(actorId) }, { actorMoviesDto ->
            actorMoviesDto.cast?.map {
                actorMoviesMapper.map(it!!)
            } ?: emptyList()
        })
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

    override fun getNowPlayingMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getNowPlayingMovies() }, { baseResponse ->
            baseResponse.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTrendingMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getTrendingMovies() }, { baseResponse ->
            baseResponse.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getGenreList(): Flow<State<List<Genre>>> {
        return wrap({ movieService.getGenreList() }, { genreResponse ->
            genreResponse.genres?.map { genreMapper.map(it) } ?: emptyList()
        })
    }

    override fun getMovieListByGenre(genreID: Int): Flow<State<List<Media>>>{
        return wrap({ movieService.getMovieListByGenre(genreID) }, { baseResponse ->
            baseResponse.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getAllMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getAllMovies() }, {
            it.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

}