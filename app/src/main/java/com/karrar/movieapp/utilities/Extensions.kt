package com.karrar.movieapp.utilities

import android.content.res.Resources
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.response.MyListsDto
import com.karrar.movieapp.data.remote.response.trailerVideosDto.ResultDto
import com.karrar.movieapp.databinding.ChipItemCategoryBinding
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.ui.category.CategoryInteractionListener
import com.karrar.movieapp.ui.home.adapter.PopularMovieAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> {
    return this
}

fun <T> MutableLiveData<Event<T>>.postEvent(content: T) {
    postValue(Event(content))
}

inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit
) {
    observe(owner) { it?.getContentIfNotHandled()?.let(onEventUnhandledContent) }
}

fun <T> ChipGroup.createChip(item: Genre, listener: T): View {
    val chipBinding: ChipItemCategoryBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.chip_item_category,
        this,
        false
    )
    chipBinding.item = item
    chipBinding.listener = listener as CategoryInteractionListener
    return chipBinding.root
}


fun List<ResultDto?>.getKey(): String? =
    this.map {
        if (it?.type == "Trailer")
            return it.key
    }.toString()


fun MyListsDto.checkIfExist(movie_id: Int): Boolean {
    this.items?.map {
        if (it?.id == movie_id) {
            return true
        }
    }
    return false
}

fun DialogFragment.setWidthPercent(percentage: Int) {
    val percent = percentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
    val percentWidth = rect.width() * percent
    dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    dialog?.setCanceledOnTouchOutside(false)
}

fun <T> LifecycleOwner.collectLast(flow: Flow<T>, action: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(action)
        }
    }
}

fun <T> LifecycleOwner.collect(flow: Flow<T>, action: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect {
                action.invoke(it)
            }
        }
    }
}

fun PopularMovieAdapter.automaticLoop(recycler: RecyclerView, timer: Long) {
    val handler = Handler(Looper.getMainLooper())
    val runnable = object : Runnable {
        var count = 0

        override fun run() {
            if (count < this@automaticLoop.itemCount) {
                recycler.scrollToPosition(count.plus(1))
                handler.postDelayed(this, timer)

                if (count == this@automaticLoop.itemCount) {
                    count = 0
                }
            }
        }
    }
    handler.postDelayed(runnable, timer)
}