package com.karrar.movieapp.domain.usecase

import androidx.paging.Pager
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Actor
import javax.inject.Inject

class GetActorsDataUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): Pager<Int,Actor>{
        return movieRepository.getActorData()
    }
}