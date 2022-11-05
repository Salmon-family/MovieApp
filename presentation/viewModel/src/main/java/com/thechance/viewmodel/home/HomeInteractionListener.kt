package com.thechance.viewmodel.home

import com.thechance.viewmodel.base.BaseInteractionListener
import com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.AllMediaType

interface HomeInteractionListener : BaseInteractionListener {
    fun onClickSeeAllActors()
}


interface TVShowInteractionListener : BaseInteractionListener {
    fun onClickTVShow(tVShowID: Int)
    fun onClickSeeTVShow(type: AllMediaType)
}