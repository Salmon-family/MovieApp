package com.karrar.movieapp.ui.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.domain.usecase.GetMovieDetailsUseCase
import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.MediaDetailsViewModel
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.*
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    state: SavedStateHandle,
) : MediaDetailsViewModel(), ActorsInteractionListener, MovieInteractionListener,
    DetailInteractionListener {

    private val args = MovieDetailsFragmentArgs.fromSavedStateHandle(state)

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

    override var ratingValue = MutableLiveData<Float>()

    private val detailItems = mutableListOf<DetailItemUIState>()

    private val _uiState = MutableStateFlow(MovieDetailsUIState())
    val uiState: StateFlow<MovieDetailsUIState> = _uiState.asStateFlow()

    init {
        getData()
    }

    override fun getData() {
        getAllDetails(args.movieId)
    }


    private fun getAllDetails(movieId: Int) {
        _uiState.update { it.copy(isLoading = true) }

        getMovieDetails(movieId)
        getMovieCast(movieId)
        getSimilarMovie(movieId)
        getRatedMovie(movieId)
        getMovieReviews(movieId)
    }

    //TODO: need extension function add error in catch block

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val result = getMovieDetailsUseCase.getMovieDetails(movieId)

                _uiState.update {
                    it.copy(
                        mediaDetailsResult = result?.toMediaDetailsUIState(), isLoading = false
                    )
                }
                updateDetailItems(DetailItemUIState.Header(_uiState.value.mediaDetailsResult))
                insertMovieToWatchHistory(_uiState.value.mediaDetailsResult)

            } catch (e: Exception) {
                val listError = listOf(Error(message = "${e.message}"))
                _uiState.update { it.copy(errors = listError) }
            }
        }
    }

    private fun getMovieCast(movieId: Int) {
        viewModelScope.launch {
            try {
                val result = getMovieDetailsUseCase.getMovieCast(movieId)
                _uiState.update {
                    it.copy(
                        movieCastResult = result.map { actor -> actor.toActorUIState() },
                        isLoading = false
                    )
                }
                updateDetailItems(DetailItemUIState.Cast(_uiState.value.movieCastResult))
            } catch (e: Exception) {
                val listError = listOf(Error(message = "${e.message}"))
                _uiState.update { it.copy(errors = listError) }
            }
        }
    }

    private fun getSimilarMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                val result = getMovieDetailsUseCase.getSimilarMovie(movieId)
                _uiState.update {
                    it.copy(
                        similarMoviesResult = result.map { media -> media.toMediaUIState() },
                        isLoading = false
                    )
                }
                updateDetailItems(DetailItemUIState.SimilarMovies(_uiState.value.similarMoviesResult))
            } catch (e: Exception) {
                val listError = listOf(Error(message = "${e.message}"))
                _uiState.update { it.copy(errors = listError) }
            }
        }
    }

    private fun getRatedMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                val result = getMovieDetailsUseCase.getRatedMovie(0)
                _uiState.update {
                    it.copy(movieGetRatedResult = result.map { rated -> rated.toRatedUIState() })
                }
                checkIfMovieRated(_uiState.value.movieGetRatedResult, movieId)
                updateDetailItems(DetailItemUIState.Rating(this@MovieDetailsViewModel))
            } catch (e: Exception) {
                val listError = listOf(Error(message = "${e.message}"))
                _uiState.update { it.copy(errors = listError) }
            }
        }
    }

    private fun getMovieReviews(movieId: Int) {
        viewModelScope.launch {
            try {
                val result = getMovieDetailsUseCase.getMovieReviews(movieId)

                _uiState.update {
                    it.copy(
                        movieReview = result.map { review -> review.toReviewUIState() },
                        isLoading = false
                    )
                }
                getThreeCommits()
                onSeeAllReviews()

            } catch (e: Exception) {
                val listError = listOf(Error(message = "${e.message}"))
                _uiState.update { it.copy(errors = listError) }
            }
        }
    }

    private fun getThreeCommits() {
        if (_uiState.value.movieReview.isNotEmpty()) {
            _uiState.value.movieReview.take(3)
                .forEach { updateDetailItems(DetailItemUIState.Comment(it)) }
            updateDetailItems(DetailItemUIState.ReviewText)
        }
    }

    private fun onSeeAllReviews() {
        if (_uiState.value.movieReview.count() > 3) updateDetailItems(
            DetailItemUIState.SeeAllReviewsButton
        )
    }

    /*
    need domain
     */
    private fun insertMovieToWatchHistory(movie: MediaDetailsUIState?) {
        viewModelScope.launch {
            movie?.let { movieDetails ->
                getMovieDetailsUseCase.insertMovie(
                    WatchHistoryEntity(
                        id = movieDetails.id,
                        posterPath = movieDetails.image,
                        movieTitle = movieDetails.name,
                        movieDuration = movieDetails.specialNumber,
                        voteAverage = movieDetails.voteAverage,
                        releaseDate = movieDetails.releaseDate,
                        mediaType = Constants.MOVIE
                    )
                )
            }
        }
    }

    private fun checkIfMovieRated(items: List<RatedUIState>?, movie_id: Int) {
        val item = items?.firstOrNull { it.id == movie_id }
        item?.let {
            if (it.rating != ratingValue.value) {
                _check.postValue(it.rating)
                ratingValue.postValue(it.rating)
            }
        }
    }


    fun onAddRating(movie_id: Int, value: Float) {
        if (_check.value != value) {
            viewModelScope.launch {
                try {
                    val result = getMovieDetailsUseCase.setRating(movie_id, value)

                    _uiState.update {
                        it.copy(
                            movieSetRatedResult = result?.toRatingStatusUIState()
                        )
                    }
                    checkStatusCode(value)
                    onShowMessage()
                } catch (e: Exception) {
                    val listError = listOf(Error(message = "${e.message}"))
                    _uiState.update { it.copy(errors = listError) }
                }
            }
        }
    }

    private fun checkStatusCode(value: Float) {
        if (_uiState.value.movieSetRatedResult?.statusCode == Constants.SUCCESS_REQUEST) {
            _check.postValue(value)
        }
    }

    private fun onShowMessage() {
        messageAppear.postValue(Event(true))
    }

    private fun updateDetailItems(item: DetailItemUIState) {
        detailItems.add(item)
        _uiState.update { it.copy(detailItemResult = detailItems) }
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

    override fun onClickSeeAllMovie(homeItemsType: HomeItemsType) {}

    override fun onClickActor(actorID: Int) {
        _clickCastEvent.postValue(Event(actorID))
    }

}