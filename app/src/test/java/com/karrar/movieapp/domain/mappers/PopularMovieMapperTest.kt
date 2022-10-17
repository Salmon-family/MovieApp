package com.karrar.movieapp.domain.mappers


import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.PopularMovie
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PopularMovieMapperTest {

    private lateinit var popularMovieMapper: PopularMovieMapper

    @BeforeAll
    fun setUp() {
        popularMovieMapper = PopularMovieMapper()
    }

    @Test
    fun should_ReturnPopularMovieMapper_when_EnterListOfMovieDTO_and_ListOfGenre() {
        // given a List Of MovieDTO object with random values
        val listOfMovieDTO = listOf(
            MovieDto(
                adult = false,
                backdropPath = "backdropPath",
                genreIds = listOf(28, 12, 16),
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
        )

        val listOfGenre = listOf(
            Genre(
                genreID = 28,
                genreName = "action"
            ),
            Genre(
                genreID = 12,
                genreName = "Adventure"
            ),
            Genre(
                genreID = 16,
                genreName = "Animation"
            )
        )
        // when map is called
        val result = popularMovieMapper.mapGenreMovie(listOfMovieDTO, listOfGenre)

        //expected
        val expectedGenreList = mutableListOf<Genre>(
            Genre(
                genreID = 28,
                genreName = "action"
            ),
            Genre(
                genreID = 12,
                genreName = "Adventure"
            ),
            Genre(
                genreID = 16,
                genreName = "Animation"
            )
        )


        val expectedPopularList = listOf(
            PopularMovie(
                movieID = 1,
                title = "title",
                imageUrl = BuildConfig.IMAGE_BASE_PATH + "backdropPath",
                movieRate = 1.0,
                genre = expectedGenreList,
            )
        )
        // then the result should be a Create List object with the same values
        assertEquals(expectedPopularList, result)
    }


    @Test
    fun should_ReturnPopularMovieMapper_when_EnterListOfMovieDTO_and_EmptyListOfGenre() {
        // given a List Of MovieDTO object with random values
        val listOfMovieDTO = listOf(
            MovieDto(
                adult = false,
                backdropPath = "backdropPath",
                genreIds = listOf(),
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
        )

        val listOfGenre = listOf<Genre>()
        // when map is called
        val result = popularMovieMapper.mapGenreMovie(listOfMovieDTO, listOfGenre)

        //expected
        val expectedGenreList = mutableListOf<Genre>()


        val expectedPopularList = listOf(
            PopularMovie(
                movieID = 1,
                title = "title",
                imageUrl = BuildConfig.IMAGE_BASE_PATH + "backdropPath",
                movieRate = 1.0,
                genre = expectedGenreList,
            )
        )
        // then the result should be a Create List object with the same values
        assertEquals(expectedPopularList, result)
    }

    @Test
    fun should_ReturnPopularMovieMapper_when_EnterEmptyListOfMovieDTO_and_EmptyListOfGenre() {
        // given a empty List Of MovieDTO object with random values
        val listOfMovieDTO = listOf<MovieDto>()
        val listOfGenre = listOf<Genre>()

        // when map is called
        val result = popularMovieMapper.mapGenreMovie(listOfMovieDTO, listOfGenre)

        // then the result should be a Create List object with the empty values
        assertEquals(emptyList<PopularMovie>(), result)
    }
}