package com.thechance.repository.mapper.movie


import com.thechance.repository.mapper.ActorMapper
import javax.inject.Inject

class LocalMovieMappersContainer @Inject constructor(
    val popularMovieMapper: PopularMovieMapper,
    val trendingMovieMapper: TrendingMovieMapper,
    val nowStreamingMovieMapper: NowStreamingMovieMapper,
    val upcomingMovieMapper: UpcomingMovieMapper,
    val mysteryMovieMapper: MysteryMovieMapper,
    val adventureMovieMapper: AdventureMovieMapper,
    val actorMapper: ActorMapper,
)