package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.SeasonDto
import com.karrar.movieapp.data.remote.response.tvShow.CreatedByDto
import com.karrar.movieapp.data.remote.response.tvShow.TvShowDetailsDto
import com.karrar.movieapp.utilities.Constants
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TvShowDetailsMapperTest {

    private lateinit var tvShowMapper: TvShowDetailsMapper
    private lateinit var seasonMapper: SeasonMapper
    private lateinit var episodeMapper: EpisodeMapper

    @BeforeAll
    fun setUp() {
        episodeMapper = EpisodeMapper()
        seasonMapper = SeasonMapper(episodeMapper)
        tvShowMapper = TvShowDetailsMapper(seasonMapper)
    }


    @Test
    fun should_ReturnTVShowDetailsMapper_when_EnterTVShowsDetailsDTO() {
        //given a TVShowsDetailsDTO object with random values
        val tvShowsDetailsDTO = TvShowDetailsDto(
            backdropPath = "backdropPath",
            createdBy = listOf(CreatedByDto(
                id = 1,
                creditId = "creditId",
                name = "name",
                gender = 1,
                profilePath = "profilePath"
            )),
            episodeRunTime = listOf(1, 2, 3),
            firstAirDate = "firstAirDate",
            genres = listOf(),
            homepage = "homepage",
            id = 1,
            inProduction = true,
            languages = listOf("en"),
            lastAirDate = "lastAirDate",
            lastEpisodeToAir = null,
            name = "name",
            nextEpisodeToAir = null,
            networks = listOf(),
            numberOfEpisodes = 1,
            numberOfSeasons = 1,
            originCountry = listOf("en"),
            originalLanguage = "en",
            originalName = "originalName",
            overview = "overview",
            popularity = 1.0,
            posterPath = "posterPath",
            productionCompanies = listOf(),
            season = listOf(
                SeasonDto(
                    airDate = "airDate",
                    episodeCount = 1,
                    id = 1,
                    name = "name",
                    overview = "overview",
                    posterPath = "posterPath",
                    seasonNumber = 1
                )
            ),
            status = "status",
            type = "type",
            voteAverage = 1.0,
            voteCount = 1,
            spokenLanguages = listOf(),
            productionCountries = listOf()
        )

        // when map is called
        val tvShowDetails = tvShowMapper.map(tvShowsDetailsDTO)

        // then the result should be a Media object with the same values
        assertEquals(tvShowsDetailsDTO.id, tvShowDetails.id)
        assertEquals(BuildConfig.IMAGE_BASE_PATH + tvShowsDetailsDTO.posterPath,
            tvShowDetails.image)
        assertEquals(tvShowsDetailsDTO.name, tvShowDetails.name)
        assertEquals(tvShowsDetailsDTO.firstAirDate?.take(4), tvShowDetails.tvShowReleaseDate)
        assertEquals(tvShowsDetailsDTO.genres?.map { it?.name }?.joinToString(", "),
            tvShowDetails.genres)
        assertEquals(tvShowsDetailsDTO.numberOfSeasons, tvShowDetails.tvShowSeasonsNumber)
        assertEquals(tvShowsDetailsDTO.voteCount, tvShowDetails.tvShowReview)
        assertEquals(tvShowsDetailsDTO.voteAverage.toString().take(3), tvShowDetails.voteAverage)
        assertEquals(tvShowsDetailsDTO.overview, tvShowDetails.overview)
        assertEquals(tvShowsDetailsDTO.season?.map { seasonMapper.map(it) }, tvShowDetails.seasons)
        assertEquals(Constants.TV_SHOWS, tvShowDetails.mediaType.value)

    }
}