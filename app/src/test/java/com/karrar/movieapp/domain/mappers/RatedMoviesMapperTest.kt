package com.karrar.movieapp.domain.mappers


import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.RatedMoviesDto
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
            releaseDate = "rele",
            title = "title",
            video = true,
            voteAverage = 1.0,
            voteCount = 1
        )
        // when map is called
        val result = ratedMoviesMapper.map(ratedMoviesDto)

        // then the result should be a Movie rated object with the same values
        assertEquals(ratedMoviesDto.id, result.id)
        assertEquals(ratedMoviesDto.title, result.title)
        assertEquals(
            BuildConfig.IMAGE_BASE_PATH + ratedMoviesDto.posterPath,
            result.posterPath
        )
        assertEquals(ratedMoviesDto.rating, result.rating)
        assertEquals(ratedMoviesDto.releaseDate, result.releaseDate)
    }
}