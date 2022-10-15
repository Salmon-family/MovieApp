package com.karrar.movieapp.domain.mappers

import javax.inject.Inject

class MapperContainer @Inject constructor(
     val movieMapper: MovieMapper,
     val popularMovieMapper: PopularMovieMapper,
     val genreMapper: GenreMapper,
     val movieDetailsMapper: MovieDetailsMapper,
     val reviewMapper: ReviewMapper,
     val trailerMapper: TrailerMapper,
     val ratedMoviesMapper: RatedMoviesMapper,
     val actorDetailsMapper: ActorDetailsMapper,
     val actorMapper: ActorMapper,
     val tvShowsMapper: TVShowMapper,
     val searchActorMapper: SearchActorMapper,
     val seriesMapper: SearchSeriesMapper,
     val searchHistoryMapper: SearchHistoryMapper,
     val itemListMapper:ItemListMapper,
     val createdListsMapper: CreatedListsMapper,
     val saveListDetailsMapper: SaveListDetailsMapper,
     val mediaMapper: TVShowMapper,
     val tvShowDetailsMapper: TvShowDetailsMapper,
     val seasonMapper: SeasonMapper,

)