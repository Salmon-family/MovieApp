package com.example.usecase.usecase.mylist

import com.example.models.models.SaveListDetails
import com.example.usecase.mappers.savedList.SaveListDetailsMapper
import com.example.usecase.utilites.ErrorUI
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
