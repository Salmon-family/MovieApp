package com.devfalah.usecases.tvShowDetails

import com.thechance.repository.AccountRepository
import javax.inject.Inject

class GetSessionIdUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    operator fun invoke(): String? {
        return accountRepository.getSessionId()
    }
}
