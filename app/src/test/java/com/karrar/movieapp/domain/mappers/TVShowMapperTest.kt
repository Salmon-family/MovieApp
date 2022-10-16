package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TVShowMapperTest {

    private lateinit var tvShowMapper: TVShowMapper

    @BeforeAll
    fun setUp() {
        tvShowMapper = TVShowMapper()
    }

    @Test
    fun should_ReturnTVShowMapper_when_EnterTVShowsDTO() {
        // given a TVShowsDTO object with random values
        val tvShowsDTO = TVShowsDTO(
            backdropPath = "backdropPath",
            firstAirDate = "firstAirDate",
            genreIds = listOf(1, 2, 3),
            id = 1,
            name = "name",
            originCountry = listOf("originCountry"),
            originalLanguage = "originalLanguage",
            originalName = "originalName",
            overview = "overview",
            popularity = 1.0,
            posterPath = "/AeyiuQUUs78bPkz18FY3AzNFF8b.jpg",
            voteAverage = 3.990000009536743,
            voteCount = 1
        )
        // when map is called
        val tvShows = tvShowMapper.map(tvShowsDTO)

        val expected = Media(
            mediaID = tvShows.mediaID,
            mediaImage = tvShows.mediaImage,
            mediaType = Constants.TV_SHOWS,
            mediaName = tvShows.mediaName,
            mediaDate = tvShows.mediaDate,
            mediaRate = tvShows.mediaRate
        )

        // then the result should be a Media object with the same values
        assertEquals(expected, tvShows)
    }

}