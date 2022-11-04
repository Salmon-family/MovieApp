package com.devfalah.usecases

import com.thechance.repository.AccountRepository
import javax.inject.Inject

class GetSessionIDUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    operator fun invoke(): String? {
        return accountRepository.getSessionId()
    }
}