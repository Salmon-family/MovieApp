package com.karrar.movieapp.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.HorizontalBaseAdapter
import com.karrar.movieapp.data.Movie
import com.karrar.movieapp.databinding.RecyclerHorizontalBinding
import com.karrar.movieapp.databinding.RecyclerMovieBinding
import com.karrar.movieapp.home.HomeViewModel


class MovieImageAdapter(items: List<Movie>) : BaseAdapter<Movie>(items) {
    override val layoutID: Int = R.layout.item_movie_image

    override fun areContentsSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.name == newItem.name
    }
}

class HorizontalImageAdapter(adapter: MovieImageAdapter, viewModel: HomeViewModel) :
    HorizontalBaseAdapter<Movie, HomeViewModel>(adapter, viewModel) {

    override val layoutID = R.layout.recycler_movie
}