package com.thechance.movie.ui.movieDetails

import com.thechance.movie.ui.base.BaseInteractionListener

interface DetailInteractionListener : BaseInteractionListener {

    fun onclickBack()

    fun onClickSave()

    fun onClickPlayTrailer()

    fun onclickViewReviews()
}