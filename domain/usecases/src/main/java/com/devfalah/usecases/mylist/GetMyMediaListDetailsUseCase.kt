package com.devfalah.usecases.mylist

import com.devfalah.models.SaveListDetails
import com.devfalah.usecases.ErrorUI
import com.devfalah.usecases.home.mappers.savedList.SaveListDetailsMapper
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