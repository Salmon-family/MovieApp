package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants.MOVIE
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MovieMapperTest {

    private lateinit var movieMapper: MovieMapper

    @BeforeAll
    fun setUp() {
        movieMapper = MovieMapper()
    }

    @Test
    fun should_ReturnMovieMapper_when_EnterMovieDTO() {
        // given a MovieDTO object with random values
        val movieDTO = MovieDto(
            adult = false,
            backdropPath = "backdropPath",
            genreIds = listOf(1, 2, 3),
            id = 1,
            originalLanguage = "en",
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
        val result = movieMapper.map(movieDTO)

        // expected
        val expectedMedia = Media(
            mediaID = 1,
            mediaImage = BuildConfig.IMAGE_BASE_PATH + "posterPath",
            mediaType = MOVIE,
            mediaName = "originalTitle",
            mediaDate = "releaseDate",
            mediaRate = 1F
        )

        // then the result should be a Media object with the same values
        assertEquals(expectedMedia, result)

    }

}