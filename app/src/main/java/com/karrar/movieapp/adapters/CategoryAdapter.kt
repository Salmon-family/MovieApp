package com.karrar.movieapp.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.data.Category

class CategoryAdapter(items: List<Category>, listener: CategoryInteractionListener) :
    BaseAdapter<Category>(items, listener) {
    override val layoutID: Int = R.layout.item_category
}

interface CategoryInteractionListener : BaseInteractionListener {
    fun onClickCategory(name: String)
}