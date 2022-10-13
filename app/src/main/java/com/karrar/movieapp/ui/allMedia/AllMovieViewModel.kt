package com.karrar.movieapp.ui.allMedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.karrar.movieapp.data.repository.AllMediaDataSource
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AllMovieViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val dataSource: AllMediaDataSource
) : BaseViewModel(), MediaInteractionListener {

    val args = AllMovieFragmentArgs.fromSavedStateHandle(state)

    val allMedia: Flow<PagingData<Media>> =
        Pager(config = config, pagingSourceFactory = { dataSource }).flow.cachedIn(viewModelScope)

    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent = _backEvent.toLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    private val _allMediaState = MutableLiveData<UIState<Boolean>>(UIState.Loading)
    val allMediaState = _allMediaState.toLiveData()

    init {
        dataSource.type = args.type
        dataSource.actorID = args.id
    }

    override fun onClickMedia(mediaId: Int) {
        _clickMovieEvent.postEvent(mediaId)
    }

    fun setErrorUiState(loadState: LoadState) {
        val result = if (loadState is LoadState.Error) {
            loadState.error.message ?: "Error"
        } else null

        if (!result.isNullOrBlank()) {
            _allMediaState.postValue(UIState.Error(result))
        }else{
            _allMediaState.postValue(UIState.Success(true))
        }
    }
}