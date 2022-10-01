package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.movieDetailsDto.reviews.AuthorDetailsDto
import com.karrar.movieapp.domain.models.User
import javax.inject.Inject

class UserMapper @Inject constructor():Mapper<AuthorDetailsDto, User> {
    override fun map(input: AuthorDetailsDto): User {
        return User(
            input.avatarPath,
            input.name,
            input.username,
            input.rating?.toFloat()?.div(2)
        )
    }
}