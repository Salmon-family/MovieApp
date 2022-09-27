package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.ui.base.HorizontalBaseAdapter
import com.karrar.movieapp.data.Types
import com.karrar.movieapp.ui.home.HomeViewModel

class HorizontalAdapter<T>(type: Types, viewModel: HomeViewModel) :
    HorizontalBaseAdapter<HomeViewModel, T>(viewModel) {

    override val layoutID: Int = initLayout(type)
    override val adapter: T = initAdapter(type, viewModel)

    private fun initLayout(type: Types): Int {
        return when (type) {
            is Types.BannerType -> {
                R.layout.recycler_banner
            }
            is Types.MovieType -> {
                R.layout.recycler_movie
            }
            Types.CategoryType -> {
                R.layout.recycler_category
            }
        }
    }

    private fun initAdapter(type: Types, viewModel: HomeViewModel): T {
        return when (type) {
            is Types.BannerType -> {
                BannerAdapter(emptyList(), viewModel) as T
            }
            is Types.MovieType -> {
                MovieImageAdapter(emptyList(), viewModel) as T
            }
            Types.CategoryType -> {
                CategoryAdapter(emptyList(), viewModel) as T
            }
        }
    }
}