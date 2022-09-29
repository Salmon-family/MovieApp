package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.PopularMovie
import com.karrar.movieapp.utilities.Constants.IMAGE_BASE_PATH

class PopularMovieMapper : Mapper<MovieDto, PopularMovie> {
    override fun map(input: MovieDto): PopularMovie {
        return PopularMovie(
            movieID = input.id ?: 0,
            title = input.title ?: "",
            movieRate = input.voteAverage ?: 0.0,
            imageUrl = (IMAGE_BASE_PATH + input.backdropPath),
            genre = input.genreIds?.map {
                Genre(it, null)
            }
        )
    }

    fun mapGenreMovie(movies: List<PopularMovie>, genreList: List<Genre>): List<PopularMovie> {
        movies.forEach { movie ->
            movie.genre?.forEach { movieGenre ->
               movieGenre.genreName = genreList.find { it.genreID == movieGenre.genreID }?.genreName
            }
        }
        return movies
    }
}