package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.domain.mappers.search.SearchSeriesMapper
import com.karrar.movieapp.domain.models.Media
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SearchSeriesMapperTest {

    private lateinit var searchSeriesMapper: SearchSeriesMapper

    @BeforeAll
    fun setUp() {
        searchSeriesMapper = SearchSeriesMapper()
    }

    @Test
    fun should_ReturnSearchSeriesMapper_when_SearchSeriesDTO() {
        // given a SearchSeriesDTO object with random values
        val searchSeriesDTO = TVShowsDTO(
            backdropPath = "backdropPath",
            firstAirDate = "firstAirDate",
            genreIds = listOf(1, 2, 3),
            id = 1,
            name = "name",
            originCountry = listOf("en"),
            originalLanguage = "en",
            originalName = "originalName",
            overview = "overview",
            popularity = 1.0,
            posterPath = "backdropPath",
            voteAverage = 1.0,
            voteCount = 1
        )

        // when map function is called
        val searchSeries = searchSeriesMapper.map(searchSeriesDTO)

        val expected = Media(
            mediaID = 1,
            mediaImage = BuildConfig.IMAGE_BASE_PATH + "backdropPath",
            mediaType = "tv",
            mediaName = "originalName",
            mediaDate = "firstAirDate",
            mediaRate = 1.0.toFloat()
        )
        // then return SearchSeriesMapper object with the same values
        assertEquals(expected, searchSeries)
    }
}