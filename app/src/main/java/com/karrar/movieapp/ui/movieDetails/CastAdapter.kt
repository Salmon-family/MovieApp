package com.karrar.movieapp.ui.movieDetails

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.domain.models.Cast


class CastAdapter(items: List<Cast>, listener: CastInteractionListener
):BaseAdapter<Cast>(items, listener) {
    override val layoutID: Int = R.layout.item_cast
}

interface CastInteractionListener : BaseInteractionListener {
    fun onClickCast(cast_id: Int)
}