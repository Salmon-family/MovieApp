package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.SeasonDto
import com.karrar.movieapp.domain.mappers.series.EpisodeMapper
import com.karrar.movieapp.domain.mappers.series.SeasonMapper
import com.karrar.movieapp.domain.models.Season
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SeasonMapperTest {

    private lateinit var seasonMapper: SeasonMapper
    private lateinit var episodeMapper: EpisodeMapper

    @BeforeAll
    fun setUp() {
        episodeMapper = EpisodeMapper()
        seasonMapper = SeasonMapper(episodeMapper)
    }

    @Test
    fun should_ReturnSeasonMapper_when_EnterSeasonDTO() {
        // given a SeasonDTO object with random values
        val seasonDTO = com.thechance.remote.response.SeasonDto(
            airDate = "airDate",
            episodeCount = 1,
            id = 1,
            name = "name",
            overview = "overview",
            posterPath = "posterPath",
            seasonNumber = 1,
            episodes = listOf()
        )

        // when map function is called
        val season = seasonMapper.map(seasonDTO)

        val expected = Season(
            seasonId = 1,
            seasonName = "name",
            seasonNumber = 1,
            seasonDescription = "overview",
            seasonYear = "airD",
            episodeCount = 1,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + "posterPath",
            episodes = listOf()
        )

        // then return SeasonMapper object with the same values
        assertEquals(expected, season)
    }
}