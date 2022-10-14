package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.review.AuthorDetailsDto
import com.karrar.movieapp.domain.models.User
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