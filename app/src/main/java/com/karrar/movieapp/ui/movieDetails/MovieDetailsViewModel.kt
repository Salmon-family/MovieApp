package com.karrar.movieapp.ui.movieDetails

import androidx.lifecycle.*
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.remote.response.movie.RatedMovie
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel(), ActorsInteractionListener, MovieInteractionListener {

    private var _movieDetails = MutableLiveData<State<MovieDetails>>()
    val movieDetails: LiveData<State<MovieDetails>> = _movieDetails

    private var _movieCast = MutableLiveData<State<List<Actor>>>()
    val movieCast: LiveData<State<List<Actor>>> = _movieCast

    private var _similarMovie = MutableLiveData<State<List<Media>>>()
    val similarMovie: LiveData<State<List<Media>>> = _similarMovie

    private var _movieReviews = MutableLiveData<State<List<Review>>>()
    val movieReviews: LiveData<State<List<Review>>> = _movieReviews

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    var clickMovieEvent: LiveData<Event<Int>> = _clickMovieEvent

    private val _clickCastEvent = MutableLiveData<Event<Int>>()
    var clickCastEvent: LiveData<Event<Int>> = _clickCastEvent

    private val _clickPlayTrailerEvent = MutableLiveData<Event<Boolean>>()
    var clickPlayTrailerEvent: LiveData<Event<Boolean>> = _clickPlayTrailerEvent

    private val _clickReviewsEvent = MutableLiveData<Event<Boolean>>()
    var clickReviewsEvent: LiveData<Event<Boolean>> = _clickReviewsEvent

    private val _clickSaveEvent = MutableLiveData<Event<Boolean>>()
    var clickSaveEvent: LiveData<Event<Boolean>> = _clickSaveEvent

    private val _check = MutableLiveData<Float>()

    val messageAppear = MutableLiveData(Event(false))

    var ratingValue = MutableLiveData<Float>()

    fun getAllDetails(movie_id: Int) {

        collectResponse(movieRepository.getMovieDetails(movie_id)) {
            _movieDetails.postValue(it)
            viewModelScope.launch {
                it.toData().let { movieDetails ->
                    if (movieDetails != null) {
                        movieRepository.insertMovie(
                            WatchHistoryEntity(
                                id = movieDetails.movieID,
                                posterPath = movieDetails.movieImage,
                                movieTitle = movieDetails.movieName,
                                movieDuration = movieDetails.duration,
                                voteAverage = movieDetails.voteAverage,
                                releaseDate = movieDetails.releaseDate,
                            )
                        )
                    }
                }
            }
        }
        collectResponse(movieRepository.getMovieCast(movie_id)) {
            _movieCast.postValue(it)
        }
        collectResponse(movieRepository.getSimilarMovie(movie_id)) {
            _similarMovie.postValue(it)
        }
        collectResponse(movieRepository.getMovieReviews(movie_id)) {
            _movieReviews.postValue(it)
        }
        collectResponse(
            movieRepository.getRatedMovie(
                14012083,
                "1d92e6a329c67e2e5e0486a0a93d5980711535b1"
            )
        ) {
            checkIfMovieRated(it.toData()?.items, movie_id)
        }

    }

    private fun checkIfMovieRated(items: List<RatedMovie>?, movie_id: Int) {
        val item = items?.firstOrNull { it.id == movie_id }
        item?.let {
            if (it.rating != ratingValue.value) {
                _check.postValue(it.rating?.toFloat())
                ratingValue.postValue(it.rating?.toFloat())
            }
        }
    }

    fun onAddRating(movie_id: Int, value: Float) {
        if (_check.value != value) {
            collectResponse(
                movieRepository.setRating(
                    movie_id, value,
                    "1d92e6a329c67e2e5e0486a0a93d5980711535b1"
                )
            )
            {
                if (it is State.Success) {
                    messageAppear.postValue(Event(true))
                    _check.postValue(value)
                }
            }
        }
    }


    fun onClickSave() {
        _clickSaveEvent.postValue(Event(true))
    }

    fun onClickPlayTrailer() {
        _clickPlayTrailerEvent.postValue(Event(true))
    }

    fun onclickBack() {
        _clickBackEvent.postValue(Event(true))
    }

    fun onclickViewReviews() {
        _clickReviewsEvent.postValue(Event(true))
    }

    override fun onClickMovie(movie_id: Int) {
        _clickMovieEvent.postValue(Event(movie_id))
    }

    override fun onClickSeeAllMovie(movieType: MovieType) {

    }

    override fun onClickActor(actorID: Int) {
        _clickCastEvent.postValue(Event(actorID))
    }

}