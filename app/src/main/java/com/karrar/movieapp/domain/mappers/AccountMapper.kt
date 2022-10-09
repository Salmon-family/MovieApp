package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.account.AccountDto
import com.karrar.movieapp.domain.models.Account
import javax.inject.Inject

class AccountMapper @Inject constructor(): Mapper<AccountDto, Account> {
    override fun map(input: AccountDto): Account {
        return Account(
            id = input.id
        )
    }
}