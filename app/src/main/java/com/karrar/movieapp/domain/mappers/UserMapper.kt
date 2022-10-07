package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.movieDetailsDto.reviews.AuthorDetailsDto
import com.karrar.movieapp.domain.models.User
import com.karrar.movieapp.utilities.checkIfGuest
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<AuthorDetailsDto, User> {
    override fun map(input: AuthorDetailsDto): User {
        return User(
            BuildConfig.IMAGE_BASE_PATH + input.avatarPath,
            input.name?.checkIfGuest(),
            input.username,
            input.rating?.toFloat()?.div(2)
        )
    }
}