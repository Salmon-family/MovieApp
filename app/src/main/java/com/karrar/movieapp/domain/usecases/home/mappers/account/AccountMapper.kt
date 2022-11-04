package com.karrar.movieapp.domain.usecases.home.mappers.account

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.account.AccountDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Account
import javax.inject.Inject

class AccountMapper @Inject constructor() : Mapper<AccountDto, Account> {
    override fun map(input: com.thechance.remote.response.account.AccountDto): Account {
        return Account(
            avatarPath = BuildConfig.IMAGE_BASE_PATH + input.avatar?.avatarPath?.avatarPath,
            name = input.name ?: "",
            username = input.username ?: ""
        )
    }
}