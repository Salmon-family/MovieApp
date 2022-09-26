package com.karrar.movieapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
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

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
   /* recyclerAdapter?.let {
        recyclerAdapter.setItems(items ?: emptyList())
        view.adapter = recyclerAdapter
    }*/
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}


