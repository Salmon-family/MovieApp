package com.thechance.viewmodel.utilities

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

//fun <T> ChipGroup.createChip(item: GenreUIState, listener: T): View {
//    val chipBinding: ChipItemCategoryBinding = DataBindingUtil.inflate(
//        LayoutInflater.from(context), R.layout.chip_item_category, this, false
//    )
//    chipBinding.item = item
//    chipBinding.listener = listener as CategoryInteractionListener
//    return chipBinding.root
//}


//fun List<com.thechance.remote.response.trailerVideosDto.ResultDto?>.getKey(): String? = this.map {
//    if (it?.type == "Trailer") return it.key
//}.toString()
//
//
//fun com.thechance.remote.response.MyListsDto.checkIfExist(movie_id: Int): Boolean {
//    this.items?.map {
//        if (it?.id == movie_id) {
//            return true
//        }
//    }
//    return false
//}

//fun DialogFragment.setWidthPercent(percentage: Int) {
//    val percent = percentage.toFloat() / 100
//    val dm = Resources.getSystem().displayMetrics
//    val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
//    val percentWidth = rect.width() * percent
//    dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
//    dialog?.setCanceledOnTouchOutside(false)
//}

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
//
//fun <T> List<T>.margeTowList(secondList: List<T>): List<T> {
//    return this.plus(secondList)
//}

//fun <T : Any> GridLayoutManager.setSpanSize(
//    footerAdapter: LoadUIStateAdapter, adapter: BasePagingAdapter<T>, spanCount: Int
//) {
//    this.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//        override fun getSpanSize(position: Int): Int {
//            return if ((position == adapter.itemCount) && footerAdapter.itemCount > 0) {
//                spanCount
//            } else {
//                1
//            }
//        }
//    }
//}

fun Date.convertToDayMonthYearFormat(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(this)
}