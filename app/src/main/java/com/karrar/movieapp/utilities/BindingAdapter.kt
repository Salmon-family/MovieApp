package com.karrar.movieapp.utilities

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.home.adapters.HorizontalAdapter


@BindingAdapter("app:posterImage")
fun bindMovieImage(image: ImageView, imageURL: String?) {
    imageURL?.let {
        Glide.with(image).load(imageURL).into(image)
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

@BindingAdapter(value = ["app:itemsAdapter", "app:isSuccess"])
fun <M : Any, T> setRecyclerAdapter(
    view: RecyclerView,
    adapters: List<Any>?,
    state: Boolean?
) {
    if (state == true) {
        adapters?.let { list ->
            list.forEach {
                (view.adapter as ConcatAdapter).addAdapter(it as HorizontalAdapter<M>)
            }
        }
    }
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


@InverseBindingAdapter(attribute = "app:currentPosition", event = "currentPositionAttributeChanged")
fun getCurrentPosition(recycler: RecyclerView): Int {
    return (recycler.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

}

@BindingAdapter(value = ["currentPositionAttributeChanged"])
fun setListener(rv: RecyclerView, l: InverseBindingListener) {
    val layoutManager = (rv.layoutManager as LinearLayoutManager)
    var prevPos = layoutManager.findFirstCompletelyVisibleItemPosition()
    rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dx == 0) {
                return
            }
            val newPos = layoutManager.findFirstCompletelyVisibleItemPosition()
            if (prevPos != newPos) {
                prevPos = newPos
                l.onChange()
            }
        }
    })
}

@BindingAdapter("currentPosition")
fun setCurrentPosition(rv: RecyclerView, pos: Int) {
    if (pos >= 0) {
        (rv.layoutManager)?.scrollToPosition(pos)
    }

}