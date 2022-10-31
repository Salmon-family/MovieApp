package com.karrar.movieapp.domain.mappers.account

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Account
import com.thechance.repository.remote.response.account.AccountDto
import javax.inject.Inject

class AccountMapper @Inject constructor() : Mapper<AccountDto, Account> {
    override fun map(input: AccountDto): Account {
        return Account(
            avatarPath = BuildConfig.IMAGE_BASE_PATH + input.avatar?.avatarPath?.avatarPath,
            name = input.name ?: "",
            username = input.username ?: ""
        )
    }
}