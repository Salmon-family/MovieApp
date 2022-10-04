package com.karrar.movieapp.ui.home

import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.home.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.home.adapters.MovieInteractionListener

interface HomeInteractionListener : BaseInteractionListener {
    fun onClickAiringToday(airingTodayID: Int)
    fun onClickSeeAllMovie(movieType: MovieType)
    fun onClickSeeAllActors()
}