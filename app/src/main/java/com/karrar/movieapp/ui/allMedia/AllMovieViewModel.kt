package com.karrar.movieapp.ui.allMedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.Pager
import androidx.paging.PagingData
import com.karrar.movieapp.data.repository.AllMediaDataSource
import com.karrar.movieapp.domain.models.Media
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

    private val args = AllMovieFragmentArgs.fromSavedStateHandle(state)
    private val actorId = args.id
    val type = args.type

    val allMedia: Flow<PagingData<Media>> =
        Pager(config = config,
            pagingSourceFactory = { dataSource }
        ).flow

    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent = _backEvent.toLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    init {
        dataSource.type = type
    }

//    init {
//        when (type) {
//            MovieType.NON -> {
//                getActorMoviesById()
//            }
//        }
//    }

//    private fun getActorMoviesById() {
//        _media.postValue(State.Loading)
//        collectResponse(movieRepository.getActorMovies(actorId)) {
//            _media.postValue(it)
//        }
//    }

    override fun onClickMedia(mediaId: Int) {
        _clickMovieEvent.postEvent(mediaId)
    }

}