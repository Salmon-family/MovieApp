package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.account.AccountDto
import com.karrar.movieapp.data.remote.response.account.Avatar
import com.karrar.movieapp.data.remote.response.account.Gravatar
import com.karrar.movieapp.domain.models.Account
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AccountMapperTest {

    private var accountMapper = AccountMapper()

    @BeforeAll
    fun setUp() {
        accountMapper = AccountMapper()
    }

    @Test
    fun should_ReturnAccountMapper_when_EnterAccountDTO() {
        // given a AccountDTO object with random values
        val accountDTO = AccountDto(
            id = 1,
            name = "name",
            username = "username",
            includeAdult = true,
            iso6391 = "iso6391",
            iso31661 = "iso31661",
            avatar = Avatar(
                gravatar = Gravatar(
                    hash = "hash"
                )
            ),
        )

        // when map function is called
        val account = AccountMapper().map(accountDTO)

        val expected = Account(
            name = "name",
            username = "username",
            avatarPath = BuildConfig.IMAGE_BASE_PATH + null,
        )

        // then return AccountMapper object with the same values
        assertEquals(expected, account)
    }
}