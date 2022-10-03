package com.karrar.movieapp.ui.home

import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.ui.base.BaseInteractionListener

interface HomeInteractionListener : BaseInteractionListener{
    fun onClickMovie(movieID: Int)
    fun onClickActor(actorID: Int)
    fun onClickAiringToday(airingTodayID: Int)
     fun onClickSeeAllMovie(movieType: MovieType)


}