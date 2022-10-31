package com.karrar.movieapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.thechance.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.actor.ActorDtoMapper
import com.karrar.movieapp.domain.models.Actor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetActorsDataUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val actorMapper: ActorDtoMapper
) {
     suspend operator fun invoke(): Flow<PagingData<Actor>>{
         return movieRepository.getActorData().flow.map { pager -> pager.map { actorMapper.map(it) } }
    }
}
