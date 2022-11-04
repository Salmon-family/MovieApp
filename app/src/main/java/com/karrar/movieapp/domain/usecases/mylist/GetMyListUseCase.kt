package com.karrar.movieapp.domain.usecases.mylist

import com.karrar.movieapp.domain.models.CreatedList
import com.karrar.movieapp.domain.usecases.home.mappers.savedList.CreatedListsMapper
import com.karrar.movieapp.utilities.ErrorUI
import com.thechance.repository.AccountRepository
import com.thechance.repository.MovieRepository
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
            response?.let {
                it.map { createdListsMapper.map(it) }
            } ?: throw Throwable(ErrorUI.EMPTY_BODY)
        } else {
            throw Throwable(ErrorUI.NO_LOGIN)
        }
    }

}