package com.karrar.movieapp.domain.mappers


import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.PopularMovie
import org.junit.jupiter.api.Assertions
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
    fun should_ReturnPopularMovieMapper_when_EnterListOfMovieDTO() {
        // given a ListOfMovieDTO object with random values
        val listOfMovieDTO = listOf(
            MovieDto(
                adult = false,
                backdropPath = "backdropPath",
                genreIds = listOf(1),
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
                genreID = 1,
                genreName = "name"
            )
        )
        // when map is called
        val result = popularMovieMapper.mapGenreMovie(listOfMovieDTO, listOfGenre)

        //expected
        val gereList = mutableListOf<Genre>(
            Genre(1, "name"),
        )


        val expectedList = listOf(
            PopularMovie(
                movieID = result.joinToString { it.movieID.toString() }.toInt(),
                title = result.joinToString { it.title },
                imageUrl = result.joinToString { it.imageUrl },
                movieRate = result.joinToString { it.movieRate.toString() }.toDouble(),
                genre = gereList,
            )
        )
        // then the result should be a Create List object with the same values
        Assertions.assertEquals(expectedList, result)
    }
}