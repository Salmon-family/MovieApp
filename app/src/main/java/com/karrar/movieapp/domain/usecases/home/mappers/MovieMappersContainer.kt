package com.karrar.movieapp.domain.usecases.home.mappers

import com.karrar.movieapp.domain.usecases.home.mappers.actor.ActorDetailsMapper
import com.karrar.movieapp.domain.usecases.home.mappers.actor.ActorDtoMapper
import com.karrar.movieapp.domain.usecases.home.mappers.movie.MovieDetailsMapper
import com.karrar.movieapp.domain.usecases.home.mappers.movie.MovieMapper
import com.karrar.movieapp.domain.usecases.home.mappers.movie.RatedMoviesMapper
import com.karrar.movieapp.domain.usecases.home.mappers.savedList.CreatedListsMapper
import com.karrar.movieapp.domain.usecases.home.mappers.savedList.ItemListMapper
import com.karrar.movieapp.domain.usecases.home.mappers.savedList.SaveListDetailsMapper
import com.karrar.movieapp.domain.usecases.home.mappers.search.SearchActorMapper
import com.karrar.movieapp.domain.usecases.home.mappers.search.SearchHistoryMapper
import com.karrar.movieapp.domain.usecases.home.mappers.search.SearchSeriesMapper
import com.thechance.repository.mapper.movie.*
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
