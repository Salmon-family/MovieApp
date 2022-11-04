package com.devfalah.usecases.mylist

import com.devfalah.models.CreatedList
import com.devfalah.usecases.ErrorUI
import com.thechance.repository.AccountRepository
import com.thechance.repository.MovieRepository
import javax.inject.Inject

class CreateMovieListUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val movieRepository: MovieRepository,
    private val getMyListUseCase: GetMyListUseCase
) {

    suspend operator fun invoke(listName: String): List<CreatedList> {
        val sessionId = accountRepository.getSessionId()
        return sessionId?.let {
            val item = movieRepository.createList(it, listName)
            if (item?.success == true) {
                getMyListUseCase()
            } else {
                throw Throwable(ErrorUI.EMPTY_BODY)
            }
        } ?: throw Throwable(ErrorUI.NO_LOGIN)
    }


}