package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.SpokenLanguageDto
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.data.remote.response.movie.MovieDetailsDto
import com.karrar.movieapp.utilities.Constants
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

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

        // then the result should be a Movie Details object with the same values
        assertEquals(movieDetailsDto.id, result.movieId)
        assertEquals(BuildConfig.IMAGE_BASE_PATH + movieDetailsDto.posterPath, result.movieImage)
        assertEquals(movieDetailsDto.title, result.movieName)
        assertEquals(movieDetailsDto.releaseDate, result.movieReleaseDate)
        assertEquals(
            movieDetailsDto.genres?.map { it?.name }?.joinToString(" , "),
            result.movieGenres
        )
        assertEquals(movieDetailsDto.runtime, result.movieDuration)
        assertEquals(movieDetailsDto.voteCount, result.movieReview)
        assertEquals(movieDetailsDto.voteAverage.toString(), result.movieVoteAverage)
        assertEquals(movieDetailsDto.overview, result.movieOverview)
        assertEquals(Constants.MOVIE, result.movieType.toString().lowercase(Locale.getDefault()))
    }
}