package com.karrar.movieapp.domain.usecase.mylist

import com.thechance.repository.AccountRepository
import com.thechance.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.savedList.CreatedListsMapper
import com.karrar.movieapp.domain.models.CreatedList
import com.karrar.movieapp.utilities.ErrorUI
import javax.inject.Inject

class GetMyListUseCase @Inject constructor(
    private val accountRepository: com.thechance.repository.AccountRepository,
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val createdListsMapper: CreatedListsMapper
) {

    suspend operator fun invoke(): List<CreatedList> {
        val sessionId = accountRepository.getSessionId()
        return if (!sessionId.isNullOrBlank()) {
            val response = movieRepository.getAllLists(sessionId)
            response?.let {
                it.map { createdListsMapper.map(it) }
            }?: throw Throwable(ErrorUI.EMPTY_BODY)
        } else {
            throw Throwable(ErrorUI.NO_LOGIN)
        }
    }

}