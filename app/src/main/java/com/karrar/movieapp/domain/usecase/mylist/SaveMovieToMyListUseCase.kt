package com.karrar.movieapp.domain.usecase.mylist

import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.utilities.checkIfExist
import javax.inject.Inject

class SaveMovieToMyListUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val movieRepository: MovieRepository
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
        } ?: "Need Login"
    }
}