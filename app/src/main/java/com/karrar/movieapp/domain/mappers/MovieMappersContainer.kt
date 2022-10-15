package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.domain.mappers.actor.ActorDetailsMapper
import com.karrar.movieapp.domain.mappers.actor.ActorMapper
import com.karrar.movieapp.domain.mappers.movie.MovieDetailsMapper
import com.karrar.movieapp.domain.mappers.movie.MovieMapper
import com.karrar.movieapp.domain.mappers.movie.PopularMovieMapper
import com.karrar.movieapp.domain.mappers.movie.RatedMoviesMapper
import com.karrar.movieapp.domain.mappers.savedList.CreatedListsMapper
import com.karrar.movieapp.domain.mappers.savedList.ItemListMapper
import com.karrar.movieapp.domain.mappers.savedList.SaveListDetailsMapper
import com.karrar.movieapp.domain.mappers.search.SearchActorMapper
import com.karrar.movieapp.domain.mappers.search.SearchHistoryMapper
import com.karrar.movieapp.domain.mappers.search.SearchSeriesMapper
import javax.inject.Inject

class MovieMappersContainer @Inject constructor(
    val movieMapper: MovieMapper,
    val popularMovieMapper: PopularMovieMapper,
    val genreMapper: GenreMapper,
    val movieDetailsMapper: MovieDetailsMapper,
    val reviewMapper: ReviewMapper,
    val trailerMapper: TrailerMapper,
    val ratedMoviesMapper: RatedMoviesMapper,
    val actorDetailsMapper: ActorDetailsMapper,
    val actorMapper: ActorMapper,
    val searchActorMapper: SearchActorMapper,
    val seriesMapper: SearchSeriesMapper,
    val searchHistoryMapper: SearchHistoryMapper,
    val itemListMapper: ItemListMapper,
    val createdListsMapper: CreatedListsMapper,
    val saveListDetailsMapper: SaveListDetailsMapper,
)
