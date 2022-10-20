package com.karrar.movieapp.ui.profile.myratings

import androidx.lifecycle.MutableLiveData
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.RatedMovies
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyRatingsViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val movieRepository: MovieRepository,
) :
    BaseViewModel(), RatedMoviesInteractionListener {

    private val _ratedMovies = MutableLiveData<UIState<List<RatedMovies>>>()
    val ratedMovies = _ratedMovies.toLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    init {
        getData()
    }

    override fun getData() {
        _ratedMovies.postValue(UIState.Loading)
        wrapWithState({
            val sessionId = accountRepository.getSessionId()
            sessionId?.let {
                val response = movieRepository.getRatedMovie(0, it)
                _ratedMovies.postValue(UIState.Success(response))
            }
        }, { _ratedMovies.postValue(UIState.Error(it.message.toString())) })
    }

    override fun onClickMovie(movieId: Int) {
        _clickMovieEvent.postEvent(movieId)
    }
}