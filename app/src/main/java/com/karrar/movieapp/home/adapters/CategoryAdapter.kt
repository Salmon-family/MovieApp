package com.karrar.movieapp.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.HorizontalBaseAdapter
import com.karrar.movieapp.home.HomeViewModel

class CategoryAdapter(items: List<String>) :
    BaseAdapter<String>(items) {

    override val layoutID: Int = R.layout.item_category

    override fun areContentsSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

class HorizontalCategoryAdapter(adapter: CategoryAdapter, viewModel: HomeViewModel) :
    HorizontalBaseAdapter<String>(adapter, viewModel)