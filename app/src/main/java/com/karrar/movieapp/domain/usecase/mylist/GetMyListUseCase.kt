package com.karrar.movieapp.domain.usecase.mylist

import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.savedList.CreatedListsMapper
import com.karrar.movieapp.domain.models.CreatedList
import com.karrar.movieapp.utilities.ErrorUI
import javax.inject.Inject

class GetMyListUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val movieRepository: MovieRepository,
    private val createdListsMapper: CreatedListsMapper
) {

    suspend operator fun invoke(): List<CreatedList> {
        val sessionId = accountRepository.getSessionId()
        return if (!sessionId.isNullOrBlank()) {
            val response = movieRepository.getAllLists(sessionId)
            ListMapper(createdListsMapper).mapList(response)
        } else {
            throw Throwable(ErrorUI.NO_LOGIN)
        }
    }

}