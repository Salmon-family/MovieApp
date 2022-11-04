package com.devfalah.usecases.searchUseCase

import com.thechance.local.database.entity.SearchHistoryEntity
import com.thechance.repository.MovieRepository
import javax.inject.Inject


class PostSaveSearchResultUseCase @Inject constructor(
    private val movieRepository: MovieRepository
    ) {
    suspend operator fun invoke(id: Int, name: String) {
        movieRepository.insertSearchItem(
            SearchHistoryEntity(
                id = id.toLong(),
                search = name
            )
        )
    }
}