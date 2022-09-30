package com.karrar.movieapp.ui.actorDetails

import com.karrar.movieapp.base.BaseInteractionListener

interface MovieInteractionListener : BaseInteractionListener {
    fun onClickMovie(actorId: Int)
}