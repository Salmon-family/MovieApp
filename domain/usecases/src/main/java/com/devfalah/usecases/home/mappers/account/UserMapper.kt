package com.devfalah.usecases.home.mappers.account

import com.thechance.remote.response.review.AuthorDetailsDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.User
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<AuthorDetailsDto, User> {
    override fun map(input: AuthorDetailsDto): User {
        return User(
            BuildConfig.IMAGE_BASE_PATH + input.avatarPath,
            input.name ?: "Guest",
            input.username ?: "Guest",
            input.rating?.toFloat()?.div(2) ?: 0F
        )
    }
}