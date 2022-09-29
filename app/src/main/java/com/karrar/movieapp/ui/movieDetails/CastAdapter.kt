package com.karrar.movieapp.ui.movieDetails

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.data.remote.response.movieDetailsDto.CastX


class CastAdapter(items: List<CastX>, listener: CastInteractionListener
):BaseAdapter<CastX>(items, listener) {
    override val layoutID: Int = R.layout.item_cast
}

interface CastInteractionListener : BaseInteractionListener {
    fun onClickCast(cast_id: Int)
}