package com.devfalah.usecases.mylist

import com.devfalah.models.CreatedList
import com.devfalah.usecases.ErrorUI
import com.devfalah.usecases.home.mappers.savedList.CreatedListsMapper
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