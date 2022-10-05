package com.karrar.movieapp.utilities

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.ui.base.BaseAdapter


@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess(view: View, state: State<T>?) {
    view.isVisible = state is State.Success
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: State<T>?) {
    view.isVisible = state is State.Loading
}

@BindingAdapter(value = ["app:hideWhenLoading"])
fun <T> hideWhenLoading(view: View, state: State<T>?) {
    view.isVisible = state !is State.Loading
}

@BindingAdapter("app:showWhenFail")
fun <T> showWhenFail(view: View, state: State<T>?) {
    view.isVisible = state is State.Error
}

@BindingAdapter("app:posterImage")
fun bindMovieImage(image: ImageView, imageURL: String?) {
    imageURL?.let {
        image.load(imageURL) {
            placeholder(R.drawable.loading)
            error(R.drawable.ic_baseline_person_24)
        }
    }
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter("app:genre")
fun setGenre(textView: TextView, genreList: List<Genre>?) {
    genreList?.let {
        textView.text = genreList.map { it.genreName }.joinToString(" . ")
    }
}
