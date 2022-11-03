package com.karrar.movieapp.domain.usecase.home.getData.movie

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.movie.NowStreamingMovieMapper
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNowStreamingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: NowStreamingMovieMapper,
) {

    suspend  operator fun invoke(): Flow<List<Media>> {
        return movieRepository.getNowStreamingMovies().map {
            it.map(movieMapper::map)
        }
    }
}