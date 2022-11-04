package com.devfalah.usecases.home.mappers.account

import com.thechance.remote.response.account.AccountDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Account
import com.thechance.repository.BuildConfig
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