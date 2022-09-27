package com.karrar.movieapp.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.HorizontalBaseAdapter
import com.karrar.movieapp.data.Types
import com.karrar.movieapp.home.HomeViewModel

class HorizontalAdapter(type: Types, viewModel: HomeViewModel) :
    HorizontalBaseAdapter<HomeViewModel>(viewModel) {

    override val layoutID: Int by lazy {
        return@lazy when (type) {
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

    override val adapter by lazy {
        return@lazy when (type) {
            is Types.BannerType -> {
                BannerAdapter(emptyList(), viewModel)
            }
            is Types.MovieType -> {
                MovieImageAdapter(emptyList(), viewModel)
            }
            Types.CategoryType -> {
                CategoryAdapter(emptyList(), viewModel)
            }
        }
    }


}