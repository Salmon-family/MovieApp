package com.karrar.movieapp.domain.usecases

import com.thechance.repository.AccountRepository
import javax.inject.Inject

class GetSessionIDUseCase @Inject constructor(
    private val accountRepository: com.thechance.repository.AccountRepository,
) {
    operator fun invoke(): String? {
        return accountRepository.getSessionId()
    }
}