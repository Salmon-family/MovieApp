package com.karrar.movieapp.ui.profile

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Account
import javax.inject.Inject

class AccountUIStateMapper @Inject constructor() : Mapper<Account, ProfileUIState> {
    override fun map(input: Account): ProfileUIState {
        return ProfileUIState(
            avatarPath = BuildConfig.IMAGE_BASE_PATH + input.avatarPath,
            name = input.name,
            username = input.username
        )
    }
}