package com.example.usecase.mappers
import com.example.usecase.mappers.search.SearchActorMapper
import com.example.usecase.mappers.actor.ActorDetailsMapper
import com.example.usecase.mappers.actor.ActorDtoMapper
import com.example.usecase.mappers.actor.ActorEntityMapper
import com.example.usecase.mappers.movie.*
import com.example.usecase.mappers.savedList.CreatedListsMapper
import com.example.usecase.mappers.savedList.ItemListMapper
import com.example.usecase.mappers.savedList.SaveListDetailsMapper
import com.example.usecase.mappers.search.SearchHistoryMapper
import com.example.usecase.mappers.search.SearchSeriesMapper
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
    val upcomingMovieMapper: UpcomingMovieMapper
)
