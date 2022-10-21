package com.karrar.movieapp.utilities

import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.*
import coil.load
import com.google.android.material.chip.ChipGroup
import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.utilities.Constants.ALL
import com.karrar.movieapp.utilities.Constants.FIRST_CATEGORY_ID
import com.karrar.movieapp.utilities.Constants.MOVIE_CATEGORIES_ID
import com.karrar.movieapp.utilities.Constants.TV_CATEGORIES_ID
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@BindingAdapter("app:isLogIN")
fun <T> isLogIN(view: View, value:Boolean) {
    if (value)
        view.isVisible = false
}


@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess2(view: View, state: UIState<T>?) {
    view.isVisible = state is UIState.Success
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading2(view: View, state: UIState<T>?) {
    view.isVisible = (state is UIState.Loading)
}

@BindingAdapter(value = ["app:showWhenNoLogin"])
fun <T> showWhenNoLogin(view: View, state: UIState<T>?) {
    view.isVisible = (state is UIState.NoLogin)
}


@BindingAdapter(value = ["app:hideWhenLoading"])
fun <T> hideWhenLoading(view: View, state: UIState<T>?) {
    view.isVisible = state !is UIState.Loading
}

@BindingAdapter("app:showWhenFail")
fun <T> showWhenFail2(view: View, state: UIState<T>?) {
    view.isVisible = state is UIState.Error
}

@BindingAdapter(value = ["app:showWhenSearch"])
fun showWhenSearch(view: View, text: String){
    view.isVisible = text.isNotBlank()
}

@BindingAdapter(value = ["app:hideWhenSearch"])
fun hideWhenSearch(view: View, text: String){
    view.isVisible = text.isBlank()
}

@BindingAdapter(value = ["app:hideWhenBlankSearch"])
fun hideWhenBlankSearch(view: View, text: String){
    if(text.isBlank()){
        view.visibility = View.INVISIBLE
    }
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
    chipList: List<Genre>?,
    categoryId: Int?,
    listener: T,
    isFirstChipSelected: Boolean?
) {
    val allMedia = Genre(FIRST_CATEGORY_ID, ALL)
    when (categoryId) {
        MOVIE_CATEGORIES_ID -> {
            chipList?.let {
                view.addView(view.createChip(allMedia, listener))
                it.forEach { genre -> view.addView(view.createChip(genre, listener)) }
            }
        }
        TV_CATEGORIES_ID -> {
            chipList?.let {
                view.addView(view.createChip(allMedia, listener))
                it.forEach { genre -> view.addView(view.createChip(genre, listener)) }
            }
        }
    }

    if (isFirstChipSelected == true) view.getChildAt(FIRST_CATEGORY_ID)?.id?.let { view.check(it) }
}

@BindingAdapter("app:isVisible")
fun <T> isVisible(view: View,isVisible :Boolean){
    view.isVisible = isVisible

}

@BindingAdapter("app:setVideoId")
fun setVideoId(view: YouTubePlayerView, videoId: String?){
    view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            videoId?.let { youTubePlayer.cueVideo(it, 0f) }
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
fun convertToHoursPattern(view: TextView, duration: Int) {
    duration.let {
        val hours = (duration / 60).toString()
        val minutes = (duration % 60).toString()
        if (hours == "0") {
            view.text = view.context.getString(R.string.minutes_pattern, minutes)
        } else if (minutes == "0") {
            view.text = view.context.getString(R.string.hours_pattern, hours)
        } else {
            view.text = view.context.getString(R.string.hours_minutes_pattern, hours, minutes)
        }
    }
}

@BindingAdapter("app:showWhenListIsEmpty")
fun <T> showWhenListIsEmpty(view: View, list: List<T>?) {
    view.isVisible = list?.isEmpty() == true
}

@BindingAdapter("app:showWhenListIsNotEmpty")
fun <T> showWhenListIsNotEmpty(view: View, list: List<T>?) {
    view.isVisible = list?.isNotEmpty() == true
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
    mediaDetails?.let {
        when(mediaDetails.mediaType){
            MediaType.MOVIE ->  setDuration(view, mediaDetails.specialNumber)
            MediaType.TV_SHOW -> view.text = view.context.getString(R.string.more_than_one_season, mediaDetails.specialNumber)
        }
    }
}

fun setDuration(view: TextView, duration: Int?) {
    val hours = duration?.div(60)
    val minutes = duration?.rem(60)
    if (hours == 0) {
        view.text = view.context.getString(R.string.minutes_pattern, minutes.toString())
    } else if (minutes == 0) {
        view.text = view.context.getString(R.string.hours_pattern, hours.toString())
    } else {
        view.text = view.context.getString(R.string.hours_minutes_pattern, hours.toString(), minutes.toString())
    }
}

@BindingAdapter("app:hideIfNotTypeOfMovie")
fun hideIfNotTypeOfMovie(view: View, mediaType: MediaType?) {
    if (mediaType != MediaType.MOVIE) view.isVisible = false
}