package com.karrar.movieapp.domain.usecase.mylist

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.savedList.SaveListDetailsMapper
import com.karrar.movieapp.domain.models.SaveListDetails
import javax.inject.Inject

class GetListDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val saveListDetailsMapper: SaveListDetailsMapper
) {

    suspend operator fun invoke(listID: Int): List<SaveListDetails> {
        val response = movieRepository.getSavedListDetails(listID)
        return response?.let {
            response.map { saveListDetailsMapper.map(it) }
        }?: throw Throwable("null output")

    }
}