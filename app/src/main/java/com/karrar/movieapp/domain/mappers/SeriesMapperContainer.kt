package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.domain.mappers.actor.ActorDtoMapper
import com.karrar.movieapp.domain.mappers.series.*
import javax.inject.Inject

class SeriesMapperContainer @Inject constructor(
    val genreMapper: GenreMapper,
    val mediaMapper: TVShowMapper,
    val tvShowDetailsMapper: TvShowDetailsMapper,
    val actorMapper: ActorDtoMapper,
    val reviewMapper: ReviewMapper,
    val seasonMapper: SeasonMapper,
    val trailerMapper: TrailerMapper,
    val ratedTvShowMapper: RatedTvShowMapper,
    val airingTodaySeriesMapper: AiringTodaySeriesMapper,
    val onTheAirSeriesMapper: OnTheAirSeriesMapper,
    val topRatedSeriesMapper: TopRatedSeriesMapper,
    val episodeMapper: EpisodeMapper
)