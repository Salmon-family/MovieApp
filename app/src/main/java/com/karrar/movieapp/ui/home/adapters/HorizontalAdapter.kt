package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.data.Types
import com.karrar.movieapp.ui.base.HorizontalBaseAdapter
import com.karrar.movieapp.ui.home.HomeViewModel

class HorizontalAdapter<T>(type: Types, viewModel: HomeViewModel) :
    HorizontalBaseAdapter<HomeViewModel, T>(viewModel) {

    override val layoutID: Int = initLayout(type)
    override val adapter: T = initAdapter(type, viewModel)

    private fun initLayout(type: Types): Int {
        return when (type) {
            is Types.PopularMovieType -> {
                R.layout.concat_item_popular_movie
            }
            is Types.MovieType -> {
                R.layout.concat_item_movie
            }
            Types.CategoryType -> {
                R.layout.concat_item_category
            }
            Types.ActorType -> {
                R.layout.concat_item_actor
            }
            Types.AiringTodayType -> {
                R.layout.cocat_item_airing_today
            }
        }
    }

    private fun initAdapter(type: Types, viewModel: HomeViewModel): T {
        return when (type) {
            is Types.PopularMovieType -> {
                PopularMovieAdapter(emptyList(), viewModel) as T
            }
            is Types.MovieType -> {
                MovieImageAdapter(emptyList(), viewModel) as T
            }
            Types.CategoryType -> {
                CategoryAdapter(emptyList(), viewModel) as T
            }
            Types.ActorType -> {
                ActorAdapter(emptyList(), viewModel) as T
            }
            Types.AiringTodayType -> {
                AiringTodayAdapter(emptyList(), viewModel) as T
            }
        }
    }
}