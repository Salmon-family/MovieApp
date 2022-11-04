package com.karrar.movieapp.domain.usecases.mylist

import com.karrar.movieapp.domain.models.SaveListDetails
import com.karrar.movieapp.domain.usecases.home.mappers.savedList.SaveListDetailsMapper
import com.karrar.movieapp.utilities.ErrorUI
import javax.inject.Inject

class GetMyMediaListDetailsUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val saveListDetailsMapper: SaveListDetailsMapper
) {
    suspend operator fun invoke(listID: Int): List<SaveListDetails> {
        return movieRepository.getSavedListDetails(listID)?.map {
            saveListDetailsMapper.map(it)
        } ?: throw Throwable(ErrorUI.EMPTY_BODY)
    }
}