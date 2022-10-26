package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.domain.mappers.actor.ActorDetailsMapper
import com.karrar.movieapp.domain.mappers.actor.ActorDtoMapper
import com.karrar.movieapp.domain.mappers.actor.ActorEntityMapper
import com.karrar.movieapp.domain.mappers.movie.*
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
    val actorMapper: ActorDtoMapper,
    val searchActorMapper: SearchActorMapper,
    val seriesMapper: SearchSeriesMapper,
    val searchHistoryMapper: SearchHistoryMapper,
    val itemListMapper: ItemListMapper,
    val createdListsMapper: CreatedListsMapper,
    val saveListDetailsMapper: SaveListDetailsMapper,
    val actorEntityMapper: ActorEntityMapper,
    val adventureMovieMapper: AdventureMovieMapper,
    val mysteryMovieMapper: MysteryMovieMapper,
    val nowStreamingMovieMapper: NowStreamingMovieMapper,
    val popularMovieEntityMapper: PopularMovieMapper,
    val trendingMapper: TrendingMovieMapper,
    val upcomingMovieMapper: UpcomingMovieMapper,



    )
