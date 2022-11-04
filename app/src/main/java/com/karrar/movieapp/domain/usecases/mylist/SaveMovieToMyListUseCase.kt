package com.karrar.movieapp.domain.usecases.mylist

import com.karrar.movieapp.utilities.ErrorUI
import com.karrar.movieapp.utilities.checkIfExist
import javax.inject.Inject

class SaveMovieToMyListUseCase @Inject constructor(
    private val accountRepository: com.thechance.repository.AccountRepository,
    private val movieRepository: com.thechance.repository.MovieRepository
) {

    suspend operator fun invoke(listID: Int, mediaId: Int): String {
        val result = movieRepository.getListDetails(listID)

        return if (result?.checkIfExist(mediaId) == true) {
            "Fail: this movie is already on the list"
        } else {
            addMovieToList(listID, mediaId)
        }
    }

    private suspend fun addMovieToList(listID: Int, mediaId: Int): String {
        val sessionID = accountRepository.getSessionId()
        return sessionID?.let {
            movieRepository.addMovieToList(it, listID, mediaId)
            "Success: The movie has been added"
        } ?: throw Throwable(ErrorUI.NO_LOGIN)
    }
}