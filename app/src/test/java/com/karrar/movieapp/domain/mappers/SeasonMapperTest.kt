package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.SeasonDto
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
        val seasonDTO = SeasonDto(
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
            seasonId = season.seasonId,
            seasonName = season.seasonName,
            seasonNumber = season.seasonNumber,
            seasonDescription = season.seasonDescription,
            seasonYear = season.seasonYear,
            episodeCount = season.episodeCount,
            imageUrl = season.imageUrl,
            episodes = season.episodes
        )

        // then return SeasonMapper object with the same values
        assertEquals(expected, season)
    }
}