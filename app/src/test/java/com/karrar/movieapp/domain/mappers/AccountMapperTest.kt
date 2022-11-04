package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.domain.mappers.account.AccountMapper
import com.devfalah.models.Account
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
        val accountDTO = com.thechance.remote.response.account.AccountDto(
            id = 1,
            name = "name",
            username = "username",
            includeAdult = true,
            iso6391 = "iso6391",
            iso31661 = "iso31661",
            avatar = com.thechance.remote.response.account.Avatar(
                gravatar = com.thechance.remote.response.account.Gravatar(
                    hash = "hash"
                )
            ),
        )

        // when map function is called
        val account = AccountMapper().map(accountDTO)

        val expected = com.devfalah.models.Account(
            name = "name",
            username = "username",
            avatarPath = BuildConfig.IMAGE_BASE_PATH + null,
        )

        // then return AccountMapper object with the same values
        assertEquals(expected, account)
    }
}