package com.karrar.movieapp.ui.search

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.Person
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class PersonAdapter(items: List<Person>, listener: PersonInteractionListener)
    : BaseAdapter<Person>(items, listener){
    override val layoutID: Int = R.layout.item_actor
}

interface PersonInteractionListener : BaseInteractionListener{
    fun onClickPerson(personID: Int)
}