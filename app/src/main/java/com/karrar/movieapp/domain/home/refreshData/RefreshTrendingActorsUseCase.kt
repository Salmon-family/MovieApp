package com.karrar.movieapp.domain.home.refreshData

import com.karrar.movieapp.data.local.mappers.ActorMapper
import com.karrar.movieapp.data.repository.MovieRepository
import javax.inject.Inject

class RefreshTrendingActorsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val actorMapper: ActorMapper
){

    suspend operator fun invoke() {
        val items = movieRepository.getTrendingActors(1).map(actorMapper::map)
        movieRepository.deleteTrendingActors()
        movieRepository.insertTrendingActors(items)
    }

}