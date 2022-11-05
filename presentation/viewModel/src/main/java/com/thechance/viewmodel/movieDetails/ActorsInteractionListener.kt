package com.thechance.viewmodel.movieDetails

import com.thechance.viewmodel.base.BaseInteractionListener

interface ActorsInteractionListener : BaseInteractionListener {
    fun onClickActor(actorID: Int)
}