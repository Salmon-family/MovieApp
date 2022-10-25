package com.karrar.movieapp.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.actor.ActorDtoMapper
import com.karrar.movieapp.domain.models.Actor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetActorsDataUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val actorMapper: ActorDtoMapper
) {
     operator fun invoke(): Flow<PagingData<Actor>>{
        return wrapper({movieRepository.getActorData()}, actorMapper::map)
    }

    private fun <T:Any>wrapper(
        data :()-> Pager<Int,T>,
        mapper : (T) -> Actor
    ) : Flow<PagingData<Actor>> {
        return data().flow.map { pager -> pager.map { mapper(it) } }
    }
}
