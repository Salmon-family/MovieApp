package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.account.AccountDto
import com.karrar.movieapp.domain.models.Account
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class AccountMapper @Inject constructor() : Mapper<AccountDto, Account> {
    override fun map(input: AccountDto): Account {
        return Account(
            avatarPath = Constants.IMAGE_BASE_PATH + input.avatar?.avatarPath?.avatarPath,
            name = input.name ?: "",
            username = input.username ?: ""
        )
    }
}