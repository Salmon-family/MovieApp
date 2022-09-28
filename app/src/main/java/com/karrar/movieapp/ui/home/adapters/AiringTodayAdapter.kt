package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.data.test.Movie

class AiringTodayAdapter(items: List<Movie>, listener: AiringTodayInteractionListener) :
    BaseAdapter<Movie>(items, listener) {
    override val layoutID: Int = R.layout.item_airing_today
}

interface AiringTodayInteractionListener : BaseInteractionListener {
    fun onClickAiringToday(airingTodayID: Int)
}