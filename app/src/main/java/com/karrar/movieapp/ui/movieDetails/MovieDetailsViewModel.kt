package com.karrar.movieapp.ui.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.movie.RatedMovie
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.MovieDetails
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val accountRepository: AccountRepository,
    state: SavedStateHandle,
) : BaseViewModel(), ActorsInteractionListener, MovieInteractionListener,
    DetailInteractionListener {

    private val args = MovieDetailsFragmentArgs.fromSavedStateHandle(state)

    private var _movieDetails = MutableLiveData<State<MovieDetails>>()
    val movieDetails = _movieDetails.toLiveData()


    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent = _clickBackEvent.toLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    private val _clickCastEvent = MutableLiveData<Event<Int>>()
    var clickCastEvent = _clickCastEvent.toLiveData()

    private val _clickPlayTrailerEvent = MutableLiveData<Event<Boolean>>()
    var clickPlayTrailerEvent = _clickPlayTrailerEvent.toLiveData()

    private val _clickReviewsEvent = MutableLiveData<Event<Boolean>>()
    var clickReviewsEvent = _clickReviewsEvent.toLiveData()

    private val _clickSaveEvent = MutableLiveData<Event<Boolean>>()
    var clickSaveEvent = _clickSaveEvent.toLiveData()

    private val _check = MutableLiveData<Float>()

    val messageAppear = MutableLiveData(Event(false))

    var ratingValue = MutableLiveData<Float>()

    val detailItemsLiveData = MutableLiveData<UIState<List<DetailItem>>>()
    private val detailItems = mutableListOf<DetailItem>()

    init {
        getAllDetails(args.movieId)
    }

    private fun getAllDetails(movieId: Int) {
        detailItemsLiveData.postValue(UIState.Loading)
        getMovieDetails(movieId)
        getMovieCast(movieId)
        getSimilarMovie(movieId)
        getMovieReviews(movieId)
        getRatedMovie(movieId)

    }

    private fun getRatedMovie(movieId: Int) {
        collectResponse(
            accountRepository.getSessionId().flatMapLatest {
                movieRepository.getRatedMovie(
                    0,
                    it.toString()
                )
            }

        ) {
            if (it is State.Success) {
                checkIfMovieRated(it.toData()?.items, movieId)
                updateDetailItems(DetailItem.Rating(this))

            }
        }
    }

    private fun getMovieReviews(movieId: Int) {
        viewModelScope.launch {
            val response = movieRepository.getMovieReviews(movieId)
            if (response.isNotEmpty()) {
                response.take(3).forEach {
                    updateDetailItems(DetailItem.Comment(it))
                }
                if (response.isNotEmpty())
                    updateDetailItems(DetailItem.ReviewText)

                if (response.count() > 3)
                    updateDetailItems(DetailItem.SeeAllReviewsButton)
            }

        }
    }

    private fun getSimilarMovie(movieId: Int) {
        viewModelScope.launch {
            val response = movieRepository.getSimilarMovie(movieId)
            if (response.isNotEmpty())
                updateDetailItems(DetailItem.SimilarMovies(response))
        }
    }

    private fun getMovieCast(movieId: Int) {
        viewModelScope.launch {
            val response = movieRepository.getMovieCast(movieId)
            if (response.isNotEmpty())
                updateDetailItems(DetailItem.Cast(response))
        }

    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val response = movieRepository.getMovieDetails(movieId)
            response?.let {
                updateDetailItems(DetailItem.Header(response))
                insertMovieToWatchHistory(response)
            }
        }
    }

    private fun insertMovieToWatchHistory(movie: MovieDetails?) {
        viewModelScope.launch {
            movie?.let { movieDetails ->
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
                accountRepository.getSessionId().flatMapLatest {
                    movieRepository.setRating(
                        movie_id, value,
                        it.toString()
                    )
                }

            )
            {
                if (it is State.Success) {
                    messageAppear.postValue(Event(true))
                    _check.postValue(value)
                }
            }
        }
    }

    private fun updateDetailItems(item: DetailItem) {
        detailItems.add(item)
        detailItemsLiveData.postValue(UIState.Success(detailItems))
    }

    override fun onClickSave() {
        _clickSaveEvent.postValue(Event(true))
    }

    override fun onClickPlayTrailer() {
        _clickPlayTrailerEvent.postValue(Event(true))
    }

    override fun onclickBack() {
        _clickBackEvent.postValue(Event(true))
    }

    override fun onclickViewReviews() {
        _clickReviewsEvent.postValue(Event(true))
    }

    override fun onClickMovie(movieId: Int) {
        _clickMovieEvent.postValue(Event(movieId))
    }

    override fun onClickSeeAllMovie(movieType: MovieType) {

    }

    override fun onClickActor(actorID: Int) {
        _clickCastEvent.postValue(Event(actorID))
    }

}