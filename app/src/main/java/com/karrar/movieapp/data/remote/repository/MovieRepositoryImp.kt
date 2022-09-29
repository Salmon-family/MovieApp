package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.ActorMapper
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.MovieMapper
import com.karrar.movieapp.domain.mappers.PopularMovieMapper
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.domain.models.PopularMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val movieMapper : MovieMapper,
    private val actorMapper: ActorMapper,
    private val genreMapper: GenreMapper
) :
    MovieRepository {

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

    override fun getUpcomingMovies(): Flow<State<List<Movie>>> {
        return wrap({ movieService.getUpcomingMovies() }, {
            it.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTopRatedMovies(): Flow<State<List<Movie>>> {
        return wrap({ movieService.getTopRatedMovies() }, {
            it.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getNowPlayingMovies(): Flow<State<List<Movie>>> {
        return wrap({ movieService.getNowPlayingMovies() }, {
            it.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTrendingMovies(): Flow<State<List<Movie>>> {
        return wrap({ movieService.getTrendingMovies() }, {
            it.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTrendingPersons(): Flow<State<List<Actor>>> {
        return wrap({ movieService.getTrendingPersons() }, {
            it.items?.map { actorMapper.map(it) } ?: emptyList()
        })
    }

    override fun getGenreList(): Flow<State<List<Genre>>> {
        return wrap({ movieService.getGenreList() }, {
            it.genres?.map { genreMapper.map(it) } ?: emptyList()
        })
    }

    private fun <I, O> wrap(
        function: suspend () -> Response<I>,
        mapper: (I) -> O,
    ): Flow<State<O>> {
        return flow {
            emit(State.Loading)
            try {
                val response = function()
                if (response.isSuccessful) {
                    val items = response.body()?.let { mapper(it) }
                    emit(State.Success(items))
                } else {
                    emit(State.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))
            }
        }
    }
}