package com.karrar.movieapp.domain.usecase.mylist

import com.thechance.repository.AccountRepository
import com.thechance.repository.MovieRepository
import com.karrar.movieapp.domain.models.CreatedList
import com.karrar.movieapp.utilities.ErrorUI
import javax.inject.Inject

class CreateMovieListUseCase @Inject constructor(
    private val accountRepository: com.thechance.repository.AccountRepository,
    private val movieRepository: com.thechance.repository.MovieRepository,
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