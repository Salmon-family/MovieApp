package com.karrar.movieapp.ui.search.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class PersonAdapter(items: List<Actor>, listener: PersonInteractionListener)
    : BaseAdapter<Actor>(items, listener){
    override val layoutID: Int = R.layout.item_actor_search
}

interface PersonInteractionListener : BaseInteractionListener{
    fun onClickPerson(personID: Int, name: String)
}