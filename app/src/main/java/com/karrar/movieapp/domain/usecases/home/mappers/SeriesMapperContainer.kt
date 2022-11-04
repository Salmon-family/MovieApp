package com.karrar.movieapp.domain.usecases.home.mappers

import com.karrar.movieapp.domain.usecases.home.mappers.actor.ActorDtoMapper
import com.karrar.movieapp.domain.usecases.home.mappers.series.*
import com.thechance.repository.mapper.series.AiringTodaySeriesMapper
import com.thechance.repository.mapper.series.OnTheAirSeriesMapper
import com.thechance.repository.mapper.series.TopRatedSeriesMapper
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