package com.karrar.movieapp.utilities

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.imageview.ShapeableImageView
import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.utilities.Constants.IMAGE_DOWNLOAD_URL
import com.squareup.picasso.Picasso

@BindingAdapter("app:movieImage")
fun bindMovieImage(image: ShapeableImageView, imageURL: String?) {
    imageURL?.let {
        Picasso.get()
            .load(imageURL)
            .error(R.mipmap.ic_launcher)
            .into(image)
    }
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter("app:showWhenLoading")
fun <T> showWhenLoading(view: View, state: State<T>?) {
    view.isVisible = state is State.Loading
}

@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess(view: View, state: State<T>?) {
    view.isVisible = state is State.Success
}

@BindingAdapter("app:showWhenFail")
fun <T> showWhenFail(view: View, state: State<T>?) {
    view.isVisible = state is State.Error
}

@BindingAdapter(value = ["app:loadImage"])
fun loadImage(view: ImageView, pathImage: String?) {
    Glide.with(view).load("$IMAGE_DOWNLOAD_URL$pathImage")
        .error(R.drawable.ic_baseline_person_24)
        .placeholder(R.drawable.loading)
        .into(view)
}


