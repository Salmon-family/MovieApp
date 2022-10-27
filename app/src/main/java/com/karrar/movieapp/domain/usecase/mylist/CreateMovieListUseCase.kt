package com.karrar.movieapp.domain.usecase.mylist

import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.savedList.CreatedListsMapper
import com.karrar.movieapp.domain.models.CreatedList
import com.karrar.movieapp.utilities.ErrorUI
import javax.inject.Inject

class CreateMovieListUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val movieRepository: MovieRepository,
    private val getMyListUseCase: GetMyListUseCase
) {

    suspend operator fun invoke(listName: String): List<CreatedList> {
        return if (listName.isNotBlank()) {
            createList(listName)
        } else {
            throw Throwable(ErrorUI.EMPTY_FIELD)
        }
    }

    private suspend fun createList(listName: String): List<CreatedList> {
        val sessionId = accountRepository.getSessionId()
        return sessionId?.let {
            val item = movieRepository.createList(it, listName)
            if (item?.success == true) {
                getMyListUseCase()
            } else {
                throw Throwable("Not Success")
            }
        } ?: throw Throwable("NoLogin")
    }
}