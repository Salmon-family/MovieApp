package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.domain.mappers.account.AccountMapper
import com.karrar.movieapp.domain.models.Account
import javax.inject.Inject

class GetAccountDetailsUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val accountMapper: AccountMapper
) {
    suspend operator fun invoke() : Account {
        val sessionId = accountRepository.getSessionId().toString()
        val account = accountRepository.getAccountDetails(sessionId = sessionId)
        return if (account != null) {
            accountMapper.map(account)
        } else {
            throw Throwable("Account is null")
        }
    }
}