package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.devfalah.types.MediaType
import com.devfalah.models.Season
import com.devfalah.models.TvShowDetails
import com.devfalah.usecases.home.mappers.series.EpisodeMapper
import com.devfalah.usecases.home.mappers.series.SeasonMapper
import com.devfalah.usecases.home.mappers.series.TvShowDetailsMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

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
        val tvShowsDetailsDTO = com.thechance.remote.response.tvShow.TvShowDetailsDto(
            backdropPath = "backdropPath",
            createdBy = listOf(
                com.thechance.remote.response.tvShow.CreatedByDto(
                    id = 1,
                    creditId = "creditId",
                    name = "name",
                    gender = 1,
                    profilePath = "profilePath"
                )
            ),
            episodeRunTime = listOf(1, 2, 3),
            firstAirDate = Date(),
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
                com.thechance.remote.response.SeasonDto(
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

        val expected = com.devfalah.models.TvShowDetails(
            tvShowId = 1,
            tvShowName = "name",
            tvShowImage = BuildConfig.IMAGE_BASE_PATH + "posterPath",
            tvShowGenres = "",
            tvShowOverview = "overview",
            tvShowReleaseDate = "firs",
            tvShowSeasons = listOf(
                com.devfalah.models.Season(
                    seasonId = 1,
                    seasonName = "name",
                    imageUrl = BuildConfig.IMAGE_BASE_PATH + "posterPath",
                    seasonNumber = 1,
                    episodeCount = 1,
                    seasonYear = "airD",
                    seasonDescription = "overview",
                    episodes = listOf()
                )
            ),
            tvShowReview = 1,
            tvShowSeasonsNumber = 1,
            tvShowVoteAverage = "1.0",
        )

        // then the result should be a Media object with the same values
        assertEquals(expected, tvShowDetails)
    }
}