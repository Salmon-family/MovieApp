package com.karrar.movieapp.ui.movieDetails

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Cast
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener


class CastAdapter(items: List<Cast>, listener: CastInteractionListener
): BaseAdapter<Cast>(items, listener) {
    override val layoutID: Int = R.layout.item_cast
}

interface CastInteractionListener : BaseInteractionListener {
    fun onClickCast(cast_id: Int)
}