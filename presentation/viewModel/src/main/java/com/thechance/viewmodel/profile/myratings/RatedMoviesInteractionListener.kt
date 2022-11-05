package com.thechance.viewmodel.profile.myratings

import com.thechance.viewmodel.base.BaseInteractionListener

interface RatedMoviesInteractionListener : BaseInteractionListener {
    fun onClickMovie(movieId: Int)
}