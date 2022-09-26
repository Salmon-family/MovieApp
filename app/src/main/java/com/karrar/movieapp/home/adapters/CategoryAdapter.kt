package com.karrar.movieapp.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.base.HorizontalBaseAdapter
import com.karrar.movieapp.data.Category
import com.karrar.movieapp.home.HomeViewModel

class CategoryAdapter(items: List<Category>, listener: CategoryInteractionListener) :
    BaseAdapter<Category>(items, listener) {
    override val layoutID: Int = R.layout.item_category
}

class HorizontalCategoryAdapter(adapter: CategoryAdapter, viewModel: HomeViewModel) :
    HorizontalBaseAdapter<Category, HomeViewModel>(adapter, viewModel) {
    override val layoutID = R.layout.recycler_category

}

interface CategoryInteractionListener : BaseInteractionListener {
    fun onClickCategory(name: String)
}