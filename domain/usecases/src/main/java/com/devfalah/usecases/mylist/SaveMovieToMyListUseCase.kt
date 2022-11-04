package com.devfalah.usecases.mylist


import com.devfalah.usecases.ErrorUI
import javax.inject.Inject

class SaveMovieToMyListUseCase @Inject constructor(
    private val accountRepository: com.thechance.repository.AccountRepository,
    private val movieRepository: com.thechance.repository.MovieRepository
) {

    suspend operator fun invoke(listID: Int, mediaId: Int): String {
        val result = movieRepository.getListDetails(listID)
        val isExist = result?.items?.filter { it.id == mediaId }?.isNotEmpty()
        return if (isExist == true) {
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