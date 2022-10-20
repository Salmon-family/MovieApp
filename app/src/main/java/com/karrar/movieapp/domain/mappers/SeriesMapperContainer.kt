package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.domain.mappers.actor.ActorMapper
import com.karrar.movieapp.domain.mappers.movie.RatedMoviesMapper
import com.karrar.movieapp.domain.mappers.series.RatedTvShowMapper
import com.karrar.movieapp.domain.mappers.series.SeasonMapper
import com.karrar.movieapp.domain.mappers.series.TVShowMapper
import com.karrar.movieapp.domain.mappers.series.TvShowDetailsMapper
import javax.inject.Inject

class SeriesMapperContainer @Inject constructor(
     val genreMapper: GenreMapper,
     val mediaMapper: TVShowMapper,
     val tvShowDetailsMapper: TvShowDetailsMapper,
     val actorMapper: ActorMapper,
     val reviewMapper: ReviewMapper,
     val seasonMapper: SeasonMapper,
     val trailerMapper: TrailerMapper,
     val ratedTvShowMapper: RatedTvShowMapper,
)