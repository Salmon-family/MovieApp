package com.karrar.movieapp.utilities

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.chip.ChipGroup
import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.databinding.ChipItemCategoryBinding
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.ui.category.CategoryInteractionListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> {
    return this
}
fun <T> MutableLiveData<Event<T>>.postEvent(content: T) {
    postValue(Event(content))
}

fun <T> ChipGroup.createChip(item: Genre, listener: T): View {
    val chipBinding: ChipItemCategoryBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.chip_item_category,
        this,
        false
    )
    chipBinding.item = item
    chipBinding.listener = listener as CategoryInteractionListener
    return chipBinding.root
}