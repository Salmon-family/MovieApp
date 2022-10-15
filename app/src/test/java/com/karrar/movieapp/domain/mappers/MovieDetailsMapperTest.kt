package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.SpokenLanguageDto
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.data.remote.response.movie.MovieDetailsDto
import com.karrar.movieapp.domain.models.MovieDetails
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MovieDetailsMapperTest {

    private lateinit var movieDetailsMapper: MovieDetailsMapper

    @BeforeAll
    fun setUp() {
        movieDetailsMapper = MovieDetailsMapper()
    }

    @Test
    fun should_ReturnMovieDetailsMapper_when_EnterMovieDetailsDto() {
        // given a MovieDetailsDto object with random values
        val movieDetailsDto = MovieDetailsDto(
            adult = false,
            backdropPath = "backdropPath",
            belongsToCollection = null,
            budget = 1,
            genres = listOf(GenreDto(18, "Drama")),
            homepage = "homepage",
            id = 1,
            imdbId = "imdbId",
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 1.0,
            posterPath = "posterPath",
            releaseDate = "rele",
            revenue = 1,
            runtime = 1,
            spokenLanguages = listOf(SpokenLanguageDto(iso6391 = "iso6391", name = "name")),
            status = "status",
            tagline = "tagline",
            title = "title",
            video = true,
            voteAverage = 1.0,
            voteCount = 1
        )
        // when map is called
        val result = movieDetailsMapper.map(movieDetailsDto)

        // expected
        val expectedGenre = MovieDetails(
            movieId = result.movieId,
            movieImage = result.movieImage,
            movieName = result.movieName,
            movieReleaseDate = result.movieReleaseDate,
            movieGenres = result.movieGenres,
            movieDuration = result.movieDuration,
            movieReview = result.movieReview,
            movieVoteAverage = result.movieVoteAverage,
            movieOverview = result.movieOverview,
            movieType = result.movieType,
        )

        // then the result should be a Movie Details object with the same values
        assertEquals(expectedGenre, result)
    }
}