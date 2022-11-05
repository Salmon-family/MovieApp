package com.thechance.viewmodel.category.com.thechance.viewmodel.search

import com.thechance.viewmodel.base.BaseInteractionListener

interface ActorSearchInteractionListener : BaseInteractionListener {
    fun onClickActorResult(personID: Int, name: String)
}