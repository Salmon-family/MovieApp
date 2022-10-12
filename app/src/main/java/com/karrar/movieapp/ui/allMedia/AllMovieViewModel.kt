package com.karrar.movieapp.ui.allMedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.karrar.movieapp.data.repository.AllMediaDataSource
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMovieViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val dataSource: AllMediaDataSource
) : BaseViewModel(), MediaInteractionListener {

    val args = AllMovieFragmentArgs.fromSavedStateHandle(state)

    val allMedia: Flow<PagingData<Media>> =
        Pager(config = config, pagingSourceFactory = { dataSource }).flow.cachedIn(viewModelScope)
    val list = MutableLiveData<PagingData<Media>>()

    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent = _backEvent.toLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    init {
        dataSource.type = args.type
        dataSource.actorID = args.id

//        viewModelScope.launch {
//            allMedia.collectLatest {
//                list.postValue(it)
//            }
//        }
//
    }

    override fun onClickMedia(mediaId: Int) {
        _clickMovieEvent.postEvent(mediaId)
    }

}