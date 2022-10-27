package com.karrar.movieapp.ui.movieDetails.mapExtension

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.User
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.UserUIState
import javax.inject.Inject

class UserUIStateMapper @Inject constructor() : Mapper<User, UserUIState> {
    override fun map(input: User): UserUIState {
        return UserUIState(
            userImage = input.userImage,
            name = input.name,
            userName = input.userName,
            rating = input.rating,
        )
    }
}