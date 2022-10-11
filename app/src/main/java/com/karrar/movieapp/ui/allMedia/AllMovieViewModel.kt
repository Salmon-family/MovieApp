package com.karrar.movieapp.ui.allMedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.UIState.Success
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
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
) : BaseViewModel(), MediaInteractionListener {

    private val args = AllMovieFragmentArgs.fromSavedStateHandle(state)
    private val actorId = args.id
    val type = args.type

    private val _media = MutableLiveData<UIState<List<Media>>>()
    val media = _media.toLiveData()

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
        _media.postValue(UIState.Loading)
        wrapWithState({
            val result = movieRepository.getActorMovies(actorId)
            _media.postValue(Success(result))
        }, {
            _media.postValue(UIState.Error(it.message.toString()))
        })
    }

    private fun getTypeMovies() {
        _media.postValue(UIState.Loading)
        val request = when (type) {
            MovieType.TRENDING -> {
                viewModelScope.launch {
                    movieRepository.getTrendingMovies2()
                }
            }
            MovieType.UPCOMING -> {
                viewModelScope.launch {
                    movieRepository.getUpcomingMovies2()
                }
            }

            MovieType.MYSTERY -> {
                viewModelScope.launch {
                    movieRepository.getMovieListByGenreID2(Constants.MYSTERY_ID)
                }
            }

            MovieType.ADVENTURE -> {
                viewModelScope.launch {
                    movieRepository.getMovieListByGenreID2(Constants.ADVENTURE_ID)
                }
            }

            MovieType.NOW_STREAMING -> {
                viewModelScope.launch {
                    movieRepository.getNowPlayingMovies2()
                }
            }

            MovieType.ON_THE_AIR -> {
                viewModelScope.launch {
                    seriesRepository.getOnTheAir2()
                }
            }

            else -> {
                throw Throwable("Error")
            }
        }
        wrapWithState({
            // here is the problem
//            _media.postValue(UIState.Success(request))
        }, {
            _media.postValue(UIState.Error(it.message.toString()))
        })
    }

    override fun onClickMedia(mediaId: Int) {
        _clickMovieEvent.postEvent(mediaId)
    }

}