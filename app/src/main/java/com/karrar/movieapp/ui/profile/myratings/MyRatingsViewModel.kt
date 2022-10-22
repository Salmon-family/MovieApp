package com.karrar.movieapp.ui.profile.myratings

import androidx.lifecycle.MutableLiveData
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyRatingsViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val movieRepository: MovieRepository,
    private val tvShowsRepository: SeriesRepository
) : BaseViewModel(), RatedMoviesInteractionListener {

    private val _rated = MutableLiveData<UIState<List<Rated>>>()
    val ratedMovies = _rated.toLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    private val _clickTVShowEvent = MutableLiveData<Event<Int>>()
    val clickTVShowEvent = _clickTVShowEvent.toLiveData()

    init {
        getData()
    }

    override fun getData() {
        _rated.postValue(UIState.Loading)
        wrapWithState({
            val sessionId = accountRepository.getSessionId()
            sessionId?.let {
                val movieResponse = movieRepository.getRatedMovie(0, it)
                val tvShowResponse = tvShowsRepository.getRatedTvShow(0, it)
                _rated.postValue(UIState.Success(movieResponse.margeTowList(tvShowResponse)))
            }
        }, { _rated.postValue(UIState.Error(it.message.toString())) })
    }

    override fun onClickMovie(mediaID: Int) {
        ratedMovies.value?.let { it ->
            val item = it.toData()?.find { it.id == mediaID }
            item?.let {
                if (it.mediaType == Constants.MOVIE) _clickMovieEvent.postEvent(mediaID)
                else _clickTVShowEvent.postEvent(mediaID)
            }
        }
    }
}