package com.karrar.movieapp.domain.home.refreshData.movie

import com.karrar.movieapp.data.local.mappers.movie.PopularMovieMapper
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.home.getData.movie.GetMovieGenreListUseCase
import javax.inject.Inject

class RefreshPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val getMovieGenreListUseCase: GetMovieGenreListUseCase,
    private val popularMovieMapper: PopularMovieMapper,
) {
    suspend operator fun invoke() {
        val genre = getMovieGenreListUseCase()
        val items = movieRepository.getAdventureMovies(1).map { popularMovieMapper.map(it, genre) }
        movieRepository.deletePopularMovies()
        movieRepository.insertPopularMovies(items)
    }
}