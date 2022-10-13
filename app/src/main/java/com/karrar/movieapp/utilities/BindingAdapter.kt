package com.karrar.movieapp.utilities

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.chip.ChipGroup
import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.MediaDetails
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.utilities.Constants.ALL
import com.karrar.movieapp.utilities.Constants.FIRST_CATEGORY_ID
import com.karrar.movieapp.utilities.Constants.MOVIE_CATEGORIES_ID
import com.karrar.movieapp.utilities.Constants.TV_CATEGORIES_ID
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso

@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess(view: View, state: State<T>?) {
    view.isVisible = state is State.Success
}

@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess2(view: View, state: UIState<T>?) {
    view.isVisible = state is UIState.Success
}


@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: State<T>?) {
    view.isVisible = (state is State.Loading)
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading2(view: View, state: UIState<T>?) {
    view.isVisible = (state is UIState.Loading)
}

@BindingAdapter(value = ["app:hideWhenLoading"])
fun <T> hideWhenLoading(view: View, state: State<T>?) {
    view.isVisible = state !is State.Loading
}

@BindingAdapter("app:showWhenFail")
fun <T> showWhenFail(view: View, state: State<T>?) {
    view.isVisible = state is State.Error
}

@BindingAdapter("app:showWhenFail")
fun <T> showWhenFail2(view: View, state: UIState<T>?) {
    view.isVisible = state is UIState.Error
}

@BindingAdapter(value = ["app:showWhenSearch"])
fun showWhenSearch(view: View, text: String){
    view.isVisible = !text.isNullOrEmpty()
}

@BindingAdapter(value = ["app:hideWhenSearch"])
fun hideWhenSearch(view: View, text: String){
    view.isVisible = text.isNullOrEmpty()
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
    view.scrollToPosition(0)
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

@BindingAdapter("app:setGenres", "app:genresId", "app:listener", "app:firstChipSelection")
fun <T> setGenresChips(
    view: ChipGroup,
    chipList: UIState<List<Genre>>?,
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

@BindingAdapter("app:movieImage")
fun bindMovieImageURL(image: ImageView, imageURL: String?) {
    imageURL?.let {
        Picasso.get()
            .load(imageURL)
            .error(R.mipmap.ic_launcher)
            .into(image)
    }
}

@BindingAdapter("app:isVisible")
fun <T> isVisible(view: View,isVisible :Boolean){
    view.isVisible = isVisible

}

@BindingAdapter("app:setVideoId")
fun setVideoId(view: YouTubePlayerView, videoId: String?){
    view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            videoId?.let { youTubePlayer.loadVideo(it, 0f) }
        }
    })
}

@BindingAdapter("app:setGenre")
fun setGenres(text: TextView, genres: List<GenreDto>?) {
    text.text = genres?.map { it.name }?.joinToString(" , ")
}

@BindingAdapter("app:setReleaseDate")
fun setReleaseDate(text: TextView, date: String?) {
    text.text = date?.take(4)
}

@BindingAdapter("app:convertToHoursPattern")
fun convertToHoursPattern(text: TextView, duration: Int?) {
    duration?.let {
        val hours = duration / 60
        val minutes = duration % 60
        "$hours h $minutes min".also { text.text = it }
    }
}

@BindingAdapter("app:showWhenListIsEmpty")
fun <T> showWhenListIsEmpty(text: TextView, list: List<T>?) {
    text.isVisible = list?.isEmpty() == true
}

@BindingAdapter(value = ["app:hideWhenLoading2"])
fun <T> hideWhenLoading2(view: View, state: UIState<T>?) {
    view.isVisible = state !is UIState.Loading
}

@BindingAdapter("app:overviewText")
fun setOverViewText(view: TextView, text: String) {
    if (text.isNotEmpty()) {
        view.text = text
    } else {
        view.text = view.context.getString(R.string.empty_overview_text)
    }
}

@BindingAdapter("app:textBasedOnMediaType")
fun setTextBasedOnMediaType(view: TextView, mediaDetails: MediaDetails?) {
    if (mediaDetails?.mediaType == MediaType.MOVIE) {
        view.text = view.context.getString(R.string.duration, mediaDetails.specialNumber)
    } else if (mediaDetails?.mediaType == MediaType.TV_SHOW && mediaDetails.specialNumber == 1) {
        view.text = view.context.getString(R.string.one_season, mediaDetails.specialNumber)
    } else if (mediaDetails?.mediaType == MediaType.TV_SHOW && mediaDetails.specialNumber > 1) {
        view.text = view.context.getString(R.string.more_than_one_season, mediaDetails.specialNumber)
    }
}