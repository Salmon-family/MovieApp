package com.karrar.movieapp.domain.allMedia

import com.devfalah.types.AllMediaType
import com.devfalah.usecases.allMedia.CheckIfMediaIsSeriesUseCase
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CheckIfMediaIsSeriesUseCaseTest {
    lateinit var checkIfMediaIsSeriesUseCase: com.devfalah.usecases.allMedia.CheckIfMediaIsSeriesUseCase


    @BeforeEach
    fun setUp() {
        checkIfMediaIsSeriesUseCase = com.devfalah.usecases.allMedia.CheckIfMediaIsSeriesUseCase()

    }


    @Test
    fun should_ReturnTrue_WhenGivenSeries() {
        //given series type
        val type = com.devfalah.types.AllMediaType.TOP_RATED

        //when
        val result = checkIfMediaIsSeriesUseCase(type)

        //then return true
        assertTrue(result)

    }

    @Test
    fun should_ReturnFalse_WhenGivenMovie() {
        //given series type
        val type = com.devfalah.types.AllMediaType.TRENDING

        //when
        val result = checkIfMediaIsSeriesUseCase(type)

        //then return true
        assertFalse(result)
    }

    @Test
    fun should_ReturnFalse_WhenGivenNON() {
        //given series type
        val type = com.devfalah.types.AllMediaType.ACTOR_MOVIES

        //when
        val result = checkIfMediaIsSeriesUseCase(type)

        //then return true
        assertFalse(result)
    }
}