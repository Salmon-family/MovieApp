package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.SeasonDto
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

        // then return SeasonMapper object with the same values
        assertEquals(seasonDTO.id, season.seasonId)
        assertEquals(BuildConfig.IMAGE_BASE_PATH + seasonDTO.posterPath, season.imageUrl)
        assertEquals(seasonDTO.name, season.seasonName)
        assertEquals(seasonDTO.airDate?.take(4), season.seasonYear)
        assertEquals(seasonDTO.seasonNumber, season.seasonNumber)
        assertEquals(seasonDTO.episodeCount, season.episodeCount)
        assertEquals(seasonDTO.overview, season.seasonDescription)
        assertEquals(seasonDTO.episodes, season.episodes)

    }
}