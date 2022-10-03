package com.karrar.movieapp.utilities

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.data.remote.response.movieDetailsDto.cast.GenreDto
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
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

@BindingAdapter("app:isVisible")
fun <T> isVisible(view: View, items: List<T>?){
    if (items != null && items.size <3)
        view.isVisible = false

}

@BindingAdapter("app:setVideoId")
fun setVideoId(view: YouTubePlayerView, videoId: String?){
    view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            videoId?.let { youTubePlayer.loadVideo(it, 0f) }
        }
    })
}

@BindingAdapter("app:isThereReview")
fun <T> isThereReview(view: View, items: List<T>?){
    if (items != null && items.isEmpty())
        view.isVisible = false
}

@BindingAdapter("app:setGenre")
fun setGenre(text: TextView, genres: List<GenreDto>?) {
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



@BindingAdapter(value = ["app:itemsWithMax"])
fun <T> setRecyclerItemsWithMaxNumberOfItems(
    view: RecyclerView,
    items: List<T>?,
) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items?.take(3) ?: emptyList())
}