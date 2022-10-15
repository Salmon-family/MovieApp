package com.karrar.movieapp.domain.mappers


import com.karrar.movieapp.data.remote.response.RatedMoviesDto
import com.karrar.movieapp.domain.models.RatedMovies
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class RatedMoviesMapperTest {

    private lateinit var ratedMoviesMapper: RatedMoviesMapper

    @BeforeAll
    fun setUp() {
        ratedMoviesMapper = RatedMoviesMapper()
    }

    @Test
    fun should_ReturnRatedMoviesMapper_when_EnterRatedMoviesDTO() {
        // given a RatedMoviesDTO object with random values
        val ratedMoviesDto = RatedMoviesDto(
            adult = false,
            backdropPath = "backdropPath",
            genreIds = listOf(1, 2, 3),
            id = 1,
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 1.0,
            posterPath = "posterPath",
            releaseDate = "releaseDate",
            title = "title",
            video = true,
            voteAverage = 1.0,
            voteCount = 1
        )
        // when map is called
        val result = ratedMoviesMapper.map(ratedMoviesDto)

        // expected
        val expectedRatedMovie = RatedMovies(
            id = result.id,
            title = result.title,
            posterPath = result.posterPath,
            rating = result.rating,
            releaseDate = result.releaseDate,
        )

        // then the result should be a Movie rated object with the same values
        assertEquals(expectedRatedMovie, result)

    }
}