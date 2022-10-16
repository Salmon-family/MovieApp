package com.karrar.movieapp.domain.mappers


import com.karrar.movieapp.data.remote.response.episode.EpisodeDto
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
            voteCount = 1
        )

        // when map function is called
        val episode = episodeMapper.map(episodeDTO)

        val expected = Episode(
            episodeId = episode.episodeId,
            episodeName = episode.episodeName,
            episodeDescription = episode.episodeDescription,
            imageUrl = episode.imageUrl,
            episodeDate = episode.episodeDate,
            episodeDuration = episode.episodeDuration,
            episodeRate = episode.episodeRate,
            episodeTotalReviews = episode.episodeTotalReviews
        )

        // then return EpisodeMapper object with the same values
        assertEquals(expected, episode)
    }
}