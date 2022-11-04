package com.devfalah.usecases

import com.devfalah.models.Account
import com.devfalah.usecases.home.mappers.account.AccountMapper
import javax.inject.Inject

class GetAccountDetailsUseCase @Inject constructor(
    private val accountRepository: com.thechance.repository.AccountRepository,
    private val accountMapper: AccountMapper
) {
    suspend operator fun invoke() : Account {
        val account = accountRepository.getAccountDetails()
        return if (account != null) {
            accountMapper.map(account)
        } else {
            throw Throwable("Account is null")
        }
    }
}