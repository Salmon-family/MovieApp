package com.karrar.movieapp.utilities

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.databinding.ChipItemCategoryBinding
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.category.CategoryInteractionListener
import com.karrar.movieapp.utilities.Constants.ALL
import com.karrar.movieapp.utilities.Constants.FIRST_CATEGORY_ID
import com.karrar.movieapp.utilities.Constants.MOVIE_CATEGORIES_ID
import com.karrar.movieapp.utilities.Constants.TV_CATEGORIES_ID
import com.squareup.picasso.Picasso

@BindingAdapter("app:posterImage")
fun bindMovieImage(image: ImageView, imageURL: String?) {
    imageURL?.let {
        Picasso.get()
            .load(imageURL)
            .error(R.mipmap.ic_launcher)
            .into(image)
    }
}

@BindingAdapter(value = ["app:itemsWithMax", "app:maxNumber"])
fun <T> setRecyclerItemsWithMaxNumberOfItems(
    view: RecyclerView,
    items: List<T>?,
    maxItemsNumber: Int
) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items?.take(maxItemsNumber) ?: emptyList())
}


@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:usePagerSnapHelper"])
fun usePagerSnapHelperWithRecycler(recycler: RecyclerView, useSnapHelper: Boolean = false) {
    if (useSnapHelper)
        PagerSnapHelper().attachToRecyclerView(recycler)
}

@BindingAdapter("app:genre")
fun setGenre(textView: TextView, genreList: List<Genre>?) {
    genreList?.let {
        textView.text = genreList.map { it.genreName }.joinToString(" . ")
    }
}

@BindingAdapter("app:isLoading")
fun <T> showWhenLoading(view: View, state: State<T>?) {
    view.isVisible = state is State.Loading
}

@BindingAdapter("app:isSuccess")
fun <T> showWhenSuccess(view: View, state: State<T>?) {
    view.isVisible = state is State.Success
}

@BindingAdapter("app:isFail")
fun <T> showWhenFail(view: View, state: State<T>?) {
    view.isVisible = state is State.Error
}

@BindingAdapter("app:setGenres", "app:genresId", "app:listener", "app:firstChipSelection")
fun <T> setGenresChips(
    view: ChipGroup,
    chipList: State<List<Genre>>?,
    categoryId: Int?,
    listener: T,
    isFirstChipSelected: Boolean?
) {
    val allMedia = Genre(FIRST_CATEGORY_ID, ALL)
    when (categoryId) {
        MOVIE_CATEGORIES_ID -> {
            chipList?.toData()?.let {
                view.addView(view.createChip(allMedia, listener))
                it.forEach { genre -> view.addView(view.createChip(genre, listener)) }
            }
        }
        TV_CATEGORIES_ID -> {
            chipList?.toData()?.let {
                view.addView(view.createChip(allMedia, listener))
                it.forEach { genre -> view.addView(view.createChip(genre, listener)) }
            }
        }
    }

    if (isFirstChipSelected == true) view.getChildAt(FIRST_CATEGORY_ID)?.id?.let { view.check(it) }
}

// should be at extensions file
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