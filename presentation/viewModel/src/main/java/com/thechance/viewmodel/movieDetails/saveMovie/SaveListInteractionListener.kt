package com.thechance.viewmodel.movieDetails.saveMovie

import com.thechance.viewmodel.base.BaseInteractionListener

interface SaveListInteractionListener : BaseInteractionListener {
    fun onClickSaveList(listID: Int)
}