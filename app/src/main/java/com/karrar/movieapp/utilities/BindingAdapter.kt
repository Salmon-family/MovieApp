package com.karrar.movieapp.utilities

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.data.remote.State
import com.squareup.picasso.Picasso

@BindingAdapter("app:mediaImage")
fun bindMediaImage(image: ImageView, imageURL: String?) {
    imageURL?.let {
        Picasso.get()
            .load(imageURL)
            .error(R.mipmap.ic_launcher)
            .into(image)
    }
}


@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: State<T>?) {
    view.isVisible = state is State.Loading
}

@BindingAdapter(value = ["app:hideWhenLoading"])
fun <T> hideWhenLoading(view: View, state: State<T>?) {
    view.isVisible = state !is State.Loading
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:setMediaVisibility"])
fun setMediaVisibility(view: RecyclerView, type: String){
    view.isVisible = type == "movie" || type == "tv"
}

@BindingAdapter(value = ["app:setPersonVisibility"])
fun setPersonVisibility(view: RecyclerView, type: String){
    view.isVisible = type == "person"
}



