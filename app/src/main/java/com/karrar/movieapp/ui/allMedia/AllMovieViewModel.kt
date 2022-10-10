package com.karrar.movieapp.ui.allMedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    private val state: SavedStateHandle
) : ViewModel(), MediaInteractionListener {

    private val args = AllMovieFragmentArgs.fromSavedStateHandle(state)
    private val actorId = args.id
    val type = args.type

    private val _media = MutableLiveData<State<List<Media?>>>()
    val media = _media.toLiveData()

    val allMedia: Flow<PagingData<Media>> =
        Pager(config = PagingConfig(pageSize = 15, prefetchDistance = 2),
            pagingSourceFactory = { AllMediaPagingDataSource(movieRepository,seriesRepository,type) }
        ).flow.cachedIn(viewModelScope)

    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent = _backEvent.toLiveData()


    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    init {
        when (type) {
            MovieType.NON -> {
                getActorMoviesById()
            }

            else -> {
                getTypeMovies()
            }
        }
    }

    private fun getActorMoviesById() {
        _media.postValue(State.Loading)
        collectResponse(movieRepository.getActorMovies(actorId)) {
            _media.postValue(it)
        }
    }

    private fun getTypeMovies() {
        _media.postValue(State.Loading)
        val request = when (type) {
            MovieType.TRENDING -> {
                movieRepository.getTrendingMovies()
            }

            MovieType.UPCOMING -> {
                movieRepository.getUpcomingMovies()
            }

            MovieType.MYSTERY -> {
                movieRepository.getMovieListByGenreID(Constants.MYSTERY_ID)
            }

            MovieType.ADVENTURE -> {
                movieRepository.getMovieListByGenreID(Constants.ADVENTURE_ID)
            }

            MovieType.NOW_STREAMING -> {
                movieRepository.getNowPlayingMovies()
            }

            MovieType.ON_THE_AIR -> {
                seriesRepository.getOnTheAir()
            }

            else -> {
                throw Throwable("Error")
            }
        }
        collectResponse(request) {
            _media.postValue(it)
        }
    }

    private fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO)
                .collect { state ->
                    function(state)
                }
        }
    }

    override fun onClickMedia(mediaId: Int) {
        _clickMovieEvent.postEvent(mediaId)
    }

}