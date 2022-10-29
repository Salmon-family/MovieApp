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
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.models.MediaDetails
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.category.uiState.ErrorUIState
import com.karrar.movieapp.ui.category.uiState.GenreUIState
import com.karrar.movieapp.ui.home.HomeRecyclerItem
import com.karrar.movieapp.ui.home.adapter.HomeAdapter
import com.karrar.movieapp.utilities.Constants.FIRST_CATEGORY_ID
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@BindingAdapter("app:showWhenListNotEmpty")
fun <T> showWhenListNotEmpty(view: View, list: List<T>) {
    view.isVisible = list.isNotEmpty() == true
}

@BindingAdapter("app:showWhenListEmpty")
fun <T> showWhenListEmpty(view: View, list: List<T>) {
    view.isVisible = list.isEmpty() == true
}

@BindingAdapter("app:hideWhenListIsEmpty")
fun <T> hideWhenListIsEmpty(view: View, list: List<T>?) {
    if (list?.isEmpty() == true) {
        view.visibility = View.INVISIBLE
    }
}

@BindingAdapter(value = ["app:error", "app:loading"])
fun <T> showWhenSuccess(view: View, error: List<T>?, loading: Boolean) {
    view.isVisible = error?.isEmpty() == true && !loading
}

@BindingAdapter(value = ["app:noError", "app:doneLoad", "app:emptyData"])
fun <T, M> showWhenNoData(view: View, error: List<T>?, loading: Boolean, data: List<M>?) {
    view.isVisible = error.isNullOrEmpty() && !loading && data.isNullOrEmpty()
}

@BindingAdapter(value = ["app:errorNotEmpty", "app:doneLoading"])
fun <T> hidWhenFail(view: View, error: List<T>?, loading: Boolean) {
    view.visibility = if (!error.isNullOrEmpty() && !loading){
        View.GONE
    }else{ View.VISIBLE}
}

@BindingAdapter("app:isListEmpty")
fun showWhenDoneLoadingAndListIsEmpty(view: View, emptyList: Boolean) {
    view.isVisible = emptyList
}

@BindingAdapter(value = ["app:showWhenNoInternet"])
fun showWhenNoInternet(view: View, error: List<ErrorUIState>) {
    view.isVisible = !error.none { it.code != ErrorUI.NEED_LOGIN }
}

@BindingAdapter(value = ["app:showWhenNoLogin"])
fun showWhenNoLogin2(view: View, error: List<ErrorUIState>) {
    view.isVisible = !error.none { it.code == ErrorUI.NEED_LOGIN }
}

@BindingAdapter("app:showWhenNoLoggedIn")
fun showWhenNoLoggedIn(view: View, isLoggedIn: Boolean) {
    view.isVisible = !isLoggedIn
}

@BindingAdapter("app:isVisible")
fun isVisible(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}

@BindingAdapter("app:hideIfTrue")
fun hideIfTrue(view: View, value: Boolean) {
    view.isVisible = !value
}

@BindingAdapter("app:isLoggedIn", "app:isFail")
fun showWhenLoggedInAndFail(view: View, isLoggedIn: Boolean, isFail: Boolean) {
    if (isLoggedIn && isFail) {
        view.isVisible = true
    } else if (isLoggedIn) {
        view.isVisible = false
    }
}

@BindingAdapter("isLogged", "isFailure")
fun showWhenIsLoggedInWithoutFail(view: View, isLoggedIn: Boolean, isFail: Boolean) {
    if (isLoggedIn && !isFail) {
        view.isVisible = true
    } else if (isFail) {
        view.isVisible = false
    }
}

//Search
@BindingAdapter(value = ["app:showWhenSearch"])
fun showWhenSearch(view: View, text: String) {
    view.isVisible = text.isNotBlank()
}

@BindingAdapter(value = ["app:hideWhenSearch"])
fun hideWhenSearch(view: View, text: String) {
    view.isVisible = text.isBlank()
}

@BindingAdapter(value = ["app:hideWhenBlankSearch"])
fun hideWhenBlankSearch(view: View, text: String) {
    if (text.isBlank()) {
        view.visibility = View.INVISIBLE
    }
}


// different

@BindingAdapter(value = ["app:homeItems"])
fun <T> setHomeRecyclerItems(view: RecyclerView, items: List<HomeRecyclerItem>?) {
    items?.forEach {
        (view.adapter as HomeAdapter).setItem(it)
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

@BindingAdapter("app:posterImage")
fun bindMovieImage(image: ImageView, imageURL: String?) {
    imageURL?.let {
        image.load(imageURL) {
            placeholder(R.drawable.loading)
            error(R.drawable.ic_baseline_person_24)
        }
    }
}

@BindingAdapter("app:showProfileWhenSuccess")
fun showWhenProfileSuccess(view: View, userName: String) {
    view.isVisible = userName.isNotEmpty()
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
        when (mediaDetails.mediaType) {
            MediaType.MOVIE -> setDuration(view, mediaDetails.specialNumber)
            MediaType.TV_SHOW -> view.text =
                view.context.getString(R.string.more_than_one_season, mediaDetails.specialNumber)
        }
    }
}

@BindingAdapter("app:setVideoId")
fun setVideoId(view: YouTubePlayerView, videoId: String?) {
    view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            videoId?.let { youTubePlayer.cueVideo(it, 0f) }
        }
    })
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

fun setDuration(view: TextView, duration: Int?) {
    val hours = duration?.div(60)
    val minutes = duration?.rem(60)
    if (hours == 0) {
        view.text = view.context.getString(R.string.minutes_pattern, minutes.toString())
    } else if (minutes == 0) {
        view.text = view.context.getString(R.string.hours_pattern, hours.toString())
    } else {
        view.text = view.context.getString(
            R.string.hours_minutes_pattern,
            hours.toString(),
            minutes.toString()
        )
    }
}

@BindingAdapter("app:setGenres", "app:listener", "app:selectedChip")
fun <T> setGenresChips(
    view: ChipGroup, chipList: List<GenreUIState>?, listener: T,
    selectedChip: Int?
) {
    chipList?.let {
        it.forEach { genre -> view.addView(view.createChip(genre, listener)) }
    }
    val index = chipList?.indexOf(chipList.find { it.genreID == selectedChip }) ?: FIRST_CATEGORY_ID
    view.getChildAt(index)?.id?.let { view.check(it) }
}

@BindingAdapter("app:genre")
fun setAllGenre(textView: TextView, genreList: List<String>?) {
    genreList?.let {
        textView.text = genreList.joinToString(" . ") { it }
    }
}

@BindingAdapter("app:hideIfNotTypeOfMovie")
fun hideIfNotTypeOfMovie(view: View, mediaType: MediaType?) {
    if (mediaType != MediaType.MOVIE) view.isVisible = false
}

// all UIState should delete

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading2(view: View, state: UIState<T>?) {
    view.isVisible = (state is UIState.Loading)
}

@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess2(view: View, state: UIState<T>?) {
    view.isVisible = state is UIState.Success
}

@BindingAdapter(value = ["app:hideWhenLoading"])
fun <T> hideWhenLoading(view: View, state: UIState<T>?) {
    view.isVisible = state !is UIState.Loading
}

@BindingAdapter("app:showWhenFail")
fun <T> showWhenFail2(view: View, state: UIState<T>?) {
    view.isVisible = state is UIState.Error
}

@BindingAdapter("app:showWhenNoResults")
fun <T> showWhenNoResults(view: View, state: UIState<T>?) {
    view.isVisible = (state == UIState.Success(false))
}