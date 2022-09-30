package com.karrar.movieapp.utilities

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.data.remote.response.movieDetailsDto.cast.Genre
import com.squareup.picasso.Picasso

@BindingAdapter("app:movieImage")
fun bindMovieImage(image: ImageView, imageURL: String?) {
    val URL = "https://image.tmdb.org/t/p/w500$imageURL"
    imageURL?.let {
        Picasso.get()
            .load(URL)
            .error(R.mipmap.ic_launcher)
            .into(image)
    }
}

@BindingAdapter("app:setGenre")
fun setGenre(text: TextView, genres: List<Genre>?) {
    text.text = genres?.map { it.name }?.joinToString(" , ")
}

@BindingAdapter("app:setReleaseDate")
fun setReleaseDate(text: TextView, date: String?) {
    text.text = date?.take(4)
}



@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}


