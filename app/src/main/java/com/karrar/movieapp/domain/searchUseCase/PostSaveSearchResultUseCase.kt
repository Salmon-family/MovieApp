package com.karrar.movieapp.domain.searchUseCase

import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.repository.MovieRepository
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