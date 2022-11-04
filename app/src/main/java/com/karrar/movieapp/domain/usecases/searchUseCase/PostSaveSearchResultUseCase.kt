package com.karrar.movieapp.domain.usecases.searchUseCase

import com.thechance.local.database.entity.SearchHistoryEntity
import com.thechance.repository.MovieRepository
import javax.inject.Inject


class PostSaveSearchResultUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository
    ) {
    suspend operator fun invoke(id: Int, name: String) {
        movieRepository.insertSearchItem(
            com.thechance.local.database.entity.SearchHistoryEntity(
                id = id.toLong(),
                search = name
            )
        )
    }
}