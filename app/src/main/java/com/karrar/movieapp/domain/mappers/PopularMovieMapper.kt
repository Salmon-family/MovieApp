package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.PopularMovie
import javax.inject.Inject

class PopularMovieMapper @Inject constructor() {

    fun mapGenreMovie(movies: List<MovieDto>?, genreList: List<Genre>): List<PopularMovie> {
        val popularMovies = mutableListOf<PopularMovie>()
        movies?.forEach { movie ->
            popularMovies.add(mapToPopularMovie(movie, genreList))
        }
        return popularMovies
    }

    private fun mapToPopularMovie(movie: MovieDto, genreList: List<Genre>): PopularMovie {
        val genres = mutableListOf<Genre>()
        movie.genreIds?.forEach { movieGenreID ->
            genres.add(
                Genre(movieGenreID, genreList.find { it.genreID == movieGenreID }?.genreName ?: "")
            )
        }
        return PopularMovie(
            movieID = movie.id ?: 0,
            title = movie.title ?: "",
            movieRate = movie.voteAverage ?: 0.0,
            imageUrl = (BuildConfig.IMAGE_BASE_PATH + movie.backdropPath),
            genre = genres
        )
    }
}