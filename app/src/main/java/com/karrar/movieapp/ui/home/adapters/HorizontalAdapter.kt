package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.enums.Type
import com.karrar.movieapp.ui.base.HorizontalBaseAdapter
import com.karrar.movieapp.ui.home.HomeViewModel

class HorizontalAdapter<T>(type: Type, viewModel: HomeViewModel) :
    HorizontalBaseAdapter<HomeViewModel, T>(viewModel) {

    override val layoutID: Int = initLayout(type)
    override val adapter: T = initAdapter(type, viewModel)

    private fun initLayout(type: Type): Int {
        return when (type) {
            Type.PopularMovieType -> {
                R.layout.concat_item_popular_movie
            }
            Type.OnTheAirType -> {
                R.layout.concat_item_on_the_air_movie
            }
            Type.TrendingMovieType -> {
                R.layout.concat_item_trending_movie
            }
            Type.NowStreaming -> {
                R.layout.concat_item_streaming_movie
            }
            Type.Upcoming -> {
                R.layout.concat_item_upcoming_movie
            }
            Type.ActorType -> {
                R.layout.concat_item_actor
            }
            Type.AiringTodayType -> {
                R.layout.cocat_item_airing_today
            }
            Type.TvShowType -> {
                R.layout.concat_item_collections
            }
            Type.MYSTERY_TYPE -> {
                R.layout.concat_item_mystery_movie
            }
            Type.ADVENTURE_TYPE -> {
                R.layout.concat_item_adventure_movie
            }
        }
    }

    private fun initAdapter(type: Type, viewModel: HomeViewModel): T {
        return when (type) {
            Type.PopularMovieType -> {
                PopularMovieAdapter(emptyList(), viewModel) as T
            }
            Type.TrendingMovieType,
            Type.MYSTERY_TYPE,
            Type.TvShowType,
            Type.OnTheAirType,
            Type.NowStreaming,
            Type.ADVENTURE_TYPE,
            Type.Upcoming -> {
                MovieAdapter(emptyList(), viewModel, type) as T
            }
            Type.ActorType -> {
                ActorAdapter(emptyList(), viewModel) as T
            }
            Type.AiringTodayType -> {
                AiringTodayAdapter(emptyList(), viewModel) as T
            }
        }
    }
}