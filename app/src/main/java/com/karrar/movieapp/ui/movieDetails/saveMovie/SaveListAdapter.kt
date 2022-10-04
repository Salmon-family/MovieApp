package com.karrar.movieapp.ui.movieDetails.saveMovie

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.data.remote.response.ListDto
import com.karrar.movieapp.domain.models.Cast


class SaveListAdapter(items: List<ListDto>, listener: SaveListInteractionListener
):BaseAdapter<ListDto>(items, listener) {
    override val layoutID: Int = R.layout.item_list
}

interface SaveListInteractionListener : BaseInteractionListener {
    fun onClickList(movie_id: Int)
}