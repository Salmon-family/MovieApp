package com.karrar.movieapp.domain.mappers


import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.episode.EpisodeDto
import com.karrar.movieapp.domain.mappers.series.EpisodeMapper
import com.karrar.movieapp.domain.models.Episode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class EpisodeMapperTest {

    private var episodeMapper = EpisodeMapper()

    @BeforeAll
    fun setUp() {
        episodeMapper = EpisodeMapper()
    }

    @Test
    fun should_ReturnEpisodeMapper_when_EnterEpisodeDTO() {
        // given a EpisodeDTO object with random values
        val episodeDTO = EpisodeDto(
            airDate = "airDate",
            episodeNumber = 1,
            id = 1,
            name = "name",
            overview = "overview",
            stillPath = "stillPath",
            voteAverage = 1.0,
            voteCount = 1,
            seasonNumber = 1,
            crew = emptyList(),
            productionCode = "productionCode",
            runtime = 1,
            showId = 1,
        )

        // when map function is called
        val episode = episodeMapper.map(episodeDTO)

        val expected = Episode(
            episodeId = 1,
            episodeName = "name",
            episodeDescription = "overview",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + "stillPath",
            episodeDate = "airDate",
            episodeDuration = 200,
            episodeRate = 1.0,
            episodeTotalReviews = "1",
            episodeNumber = 1
        )

        // then return EpisodeMapper object with the same values
        assertEquals(expected, episode)
    }
}