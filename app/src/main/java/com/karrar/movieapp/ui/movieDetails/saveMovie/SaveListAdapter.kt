package com.karrar.movieapp.ui.movieDetails.saveMovie

import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.response.CreatedListDto
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class SaveListAdapter(items: List<CreatedListDto>, listener: SaveListInteractionListener
): BaseAdapter<CreatedListDto>(items, listener) {
    override val layoutID: Int = R.layout.item_list
}

interface SaveListInteractionListener : BaseInteractionListener {
    fun onClickList(list_id: Int)
}