package com.example.usecase.login

import com.thechance.repository.AccountRepository
import javax.inject.Inject

class GetRequestTokenUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): String {
        return accountRepository.getRequestToken()
    }
}
