package com.karrar.movieapp.domain.usecases.home.mappers.account

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.review.AuthorDetailsDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.User
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<AuthorDetailsDto, User> {
    override fun map(input: com.thechance.remote.response.review.AuthorDetailsDto): User {
        return User(
            BuildConfig.IMAGE_BASE_PATH + input.avatarPath,
            input.name ?: "Guest",
            input.username ?: "Guest",
            input.rating?.toFloat()?.div(2) ?: 0F
        )
    }
}