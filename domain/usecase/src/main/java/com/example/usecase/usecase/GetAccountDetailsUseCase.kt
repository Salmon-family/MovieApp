package com.example.usecase.usecase

import com.example.models.models.Account
import com.example.usecase.mappers.account.AccountMapper
import com.thechance.repository.AccountRepository
import javax.inject.Inject

class GetAccountDetailsUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val accountMapper: AccountMapper
) {
    suspend operator fun invoke(): Account {
        val sessionId = accountRepository.getSessionId().toString()
        val account = accountRepository.getAccountDetails(sessionId = sessionId)
        return if (account != null) {
            accountMapper.map(account)
        } else {
            throw Throwable("Account is null")
        }
    }
}
