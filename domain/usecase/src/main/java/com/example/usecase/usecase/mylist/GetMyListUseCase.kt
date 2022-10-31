package com.example.usecase.usecase.mylist

import com.example.models.models.CreatedList
import com.example.usecase.mappers.savedList.CreatedListsMapper
import com.example.usecase.utilites.ErrorUI
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
            } ?: throw Throwable(ErrorUI.EMPTY_BODY)
        } else {
            throw Throwable(ErrorUI.NO_LOGIN)
        }
    }
}
