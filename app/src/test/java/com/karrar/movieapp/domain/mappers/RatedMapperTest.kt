package com.karrar.movieapp.domain.mappers


import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.RatedMoviesDto
import com.karrar.movieapp.domain.mappers.movie.RatedMoviesMapper
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.utilities.Constants
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class RatedMapperTest {

    private lateinit var ratedMoviesMapper: RatedMoviesMapper

    @BeforeAll
    fun setUp() {
        ratedMoviesMapper = RatedMoviesMapper()
    }

    @Test
    fun should_ReturnRatedMoviesMapper_when_EnterRatedMoviesDTO() {
        // given a RatedMoviesDTO object with random values
        val ratedMoviesDto = com.thechance.remote.response.RatedMoviesDto(
            adult = false,
            backdropPath = "backdropPath",
            genreIds = listOf(1, 2, 3),
            id = 1,
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 1.0,
            posterPath = "posterPath",
            rating = 1f,
            releaseDate = "releaseDate",
            title = "title",
            video = true,
            voteAverage = 1.0,
            voteCount = 1
        )
        // when map is called
        val result = ratedMoviesMapper.map(ratedMoviesDto)

        // expected
        val expectedRatedMovie = Rated(
            id = 1,
            title = "title",
            posterPath = BuildConfig.IMAGE_BASE_PATH + "posterPath",
            rating = 1f,
            releaseDate = "releaseDate",
            mediaType = Constants.MOVIE
        )

        // then the result should be a Movie rated object with the same values
        assertEquals(expectedRatedMovie, result)

    }
}