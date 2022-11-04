package com.karrar.movieapp.domain.usecases.home.getData.movie

import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.usecases.home.mappers.movie.NowStreamingMovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNowStreamingMoviesUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val movieMapper: NowStreamingMovieMapper,
) {

    suspend  operator fun invoke(): Flow<List<Media>> {
        return movieRepository.getNowStreamingMovies().map {
            it.map(movieMapper::map)
        }
    }
}