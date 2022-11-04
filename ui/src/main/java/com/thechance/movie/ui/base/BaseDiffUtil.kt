package com.thechance.movie.ui.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val checkIfSameItem: (oldItem: T, newItem: T) -> Boolean,
    private val checkIfSameContent: (oldItem: T, newItem: T) -> Boolean
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return checkIfSameItem(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return checkIfSameContent(oldList[oldItemPosition], newList[newItemPosition])
    }

}