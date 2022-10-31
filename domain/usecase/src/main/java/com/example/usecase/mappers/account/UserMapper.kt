package com.example.usecase.mappers.account

import com.example.models.models.User
import com.example.usecase.mappers.Mapper
import com.thechance.repository.BuildConfig
import com.thechance.repository.remote.response.review.AuthorDetailsDto
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
