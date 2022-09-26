package com.karrar.movieapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.home.adapters.CategoryAdapter
import com.karrar.movieapp.home.adapters.MovieImageAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:movieImage")
fun bindMovieImage(image: ImageView, imageURL: String?) {
    imageURL?.let {
        Picasso.get()
            .load(imageURL)
            .error(R.mipmap.ic_launcher)
            .into(image)
    }
}

@BindingAdapter(value = ["app:items", "app:adapter"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?, adapter: BaseAdapter<T>?) {
    adapter?.setItems(items ?: emptyList())
    view.adapter = adapter
//    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}


