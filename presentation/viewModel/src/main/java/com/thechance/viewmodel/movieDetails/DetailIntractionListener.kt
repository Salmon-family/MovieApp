package com.thechance.viewmodel.movieDetails

import com.thechance.viewmodel.base.BaseInteractionListener

interface DetailInteractionListener : BaseInteractionListener {

    fun onclickBack()

    fun onClickSave()

    fun onClickPlayTrailer()

    fun onclickViewReviews()
}