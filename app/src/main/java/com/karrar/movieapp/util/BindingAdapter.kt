package com.karrar.movieapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.karrar.movieapp.R
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