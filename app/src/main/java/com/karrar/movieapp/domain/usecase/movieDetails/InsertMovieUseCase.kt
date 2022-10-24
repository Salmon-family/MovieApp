package com.karrar.movieapp.domain.usecase.movieDetails

import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.repository.MovieRepository
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke(movie: WatchHistoryEntity){
        return movieRepository.insertMovie(movie)
    }
}