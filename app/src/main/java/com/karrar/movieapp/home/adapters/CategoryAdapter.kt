package com.karrar.movieapp.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.HorizontalBaseAdapter
import com.karrar.movieapp.data.Category
import com.karrar.movieapp.home.HomeViewModel

class CategoryAdapter(items: List<Category>) : BaseAdapter<Category>(items) {

    override val layoutID: Int = R.layout.item_category

    override fun areContentsSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.categoryName == newItem.categoryName
    }
}

class HorizontalCategoryAdapter(adapter: CategoryAdapter, viewModel: HomeViewModel) :
    HorizontalBaseAdapter<BaseAdapter<Category>, HomeViewModel>(adapter, viewModel) {

    override val layoutID = R.layout.recycler_horizontal
}