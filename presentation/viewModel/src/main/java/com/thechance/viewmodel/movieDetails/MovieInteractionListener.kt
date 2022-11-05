package com.thechance.viewmodel.movieDetails

import com.thechance.viewmodel.base.BaseInteractionListener
import com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.HomeItemsType

interface MovieInteractionListener : BaseInteractionListener {
    fun onClickMovie(movieId: Int)
    fun onClickSeeAllMovie(homeItemsType: HomeItemsType)
}
