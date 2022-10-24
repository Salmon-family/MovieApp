package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.repository.AccountRepository
import javax.inject.Inject

class GetSessionIdUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    operator fun invoke(): String? {
        return accountRepository.getSessionId()
    }
}