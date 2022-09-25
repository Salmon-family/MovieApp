package com.karrar.movieapp.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter

class HorizontalAdapter(items: List<String>) :
    BaseAdapter<String>(items) {

    override val layoutID: Int = R.layout.view_horizontal

    override fun areContentsSame(oldItem: String, newItem: String): Boolean {
        return true
    }
}
