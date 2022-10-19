package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.data.remote.response.review.AuthorDetailsDto
import com.karrar.movieapp.domain.models.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UserMapperTest {

    private lateinit var userMapper: UserMapper

    @BeforeAll
    fun setUp() {
        userMapper = UserMapper()
    }

    @Test
    fun should_ReturnUserMapper_when_EnterUserDTO() {
        // given a ActorDetailsDTO object with random values
        val actorDetailsDto = AuthorDetailsDto(
            name = "name",
            username = "username",
            avatarPath = "avatarPath",
            rating = 1.0
        )

        // when map function is called
        val user = userMapper.map(actorDetailsDto)

        val expected = User(
            userName = "username",
            userImage = BuildConfig.IMAGE_BASE_PATH + "avatarPath",
            name = "name",
            rating = 0.5.toFloat()
        )

        // then return UserMapper object with the same values
        assertEquals(expected, user)
    }
}