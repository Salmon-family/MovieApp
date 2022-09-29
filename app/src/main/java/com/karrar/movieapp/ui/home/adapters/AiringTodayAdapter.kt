package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class AiringTodayAdapter(items: List<Movie>, listener: AiringTodayInteractionListener) :
    BaseAdapter<Movie>(items, listener, null) {
    override val layoutID: Int = R.layout.item_airing_today
}

interface AiringTodayInteractionListener : BaseInteractionListener {
    fun onClickAiringToday(airingTodayID: Int)
}