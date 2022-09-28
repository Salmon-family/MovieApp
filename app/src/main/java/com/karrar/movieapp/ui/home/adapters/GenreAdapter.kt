package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.domain.models.Genre

class GenreAdapter(items: List<Genre>, listener: GenreInteractionListener) :
    BaseAdapter<Genre>(items, listener,null) {
    override val layoutID: Int = R.layout.item_genre
}

interface GenreInteractionListener : BaseInteractionListener {
    fun onClickGenre(genreID: Int)
}