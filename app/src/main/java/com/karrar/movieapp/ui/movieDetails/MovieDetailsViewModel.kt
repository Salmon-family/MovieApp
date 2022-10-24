package com.karrar.movieapp.ui.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.domain.models.MovieDetails
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.domain.usecase.movieDetails.*
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.MediaDetailsViewModel
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
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getSimilarMovieUseCase: GetSimilarMovieUseCase,
    private val getRatedMovieUseCase: GetRatedMovieUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val insertMovieUseCase: InsertMovieUseCase,
    private val setRatingUseCase: SetRatingUseCase,
    private val getSessionIdUseCase: GetSessionIdUseCase,
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

    private val _detailItemsLiveData = MutableLiveData<UIState<Boolean>>()
    val detailsItemLiveData = _detailItemsLiveData.toLiveData()

    private val detailItems = mutableListOf<DetailItem>()

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

    private fun getMovieDetails(movieId: Int) {
        wrapWithState(
            {
                _uiState.update {
                    it.copy(
                        movieDetailsResult = getMovieDetailsUseCase(movieId),
                        isLoading = false
                    )
                }
                updateDetailItems(DetailItem.Header(_uiState.value.movieDetailsResult))
                insertMovieToWatchHistory(_uiState.value.movieDetailsResult)
                _detailItemsLiveData.postValue(UIState.Success(true))
            }, {
                _detailItemsLiveData.postValue(UIState.Error(_uiState.value.errors.joinToString { it.message }))
            })
    }

    private fun getMovieCast(movieId: Int) {
        wrapWithState({
            _uiState.update {
                it.copy(
                    movieCastResult = getMovieCastUseCase(movieId),
                    isLoading = false
                )
            }
            updateDetailItems(DetailItem.Cast(_uiState.value.movieCastResult))
        })
    }

    private fun getSimilarMovie(movieId: Int) {
        wrapWithState(
            {
                _uiState.update {
                    it.copy(
                        similarMoviesResult = getSimilarMovieUseCase(movieId),
                        isLoading = false
                    )
                }
                updateDetailItems(DetailItem.SimilarMovies(_uiState.value.similarMoviesResult))
            }
        )
    }

    private fun getRatedMovie(movieId: Int) {
        wrapWithState({
            _uiState.update { it.copy(sessionIdResult = getSessionIdUseCase(), isLoading = false) }
            _uiState.update {
                it.copy(
                    movieGetRatedResult = getRatedMovieUseCase(
                        0,
                        _uiState.value.sessionIdResult ?: ""
                    )
                )
            }
            checkIfMovieRated(_uiState.value.movieGetRatedResult, movieId)
            updateDetailItems(DetailItem.Rating(this@MovieDetailsViewModel))
        })
    }

    private fun getMovieReviews(movieId: Int) {
        wrapWithState({
            _uiState.update {
                it.copy(
                    movieReview = getMovieReviewsUseCase(movieId),
                    isLoading = false
                )
            }
            if (_uiState.value.movieReview.isNotEmpty()) {
                _uiState.value.movieReview.take(3)
                    .forEach { updateDetailItems(DetailItem.Comment(it)) }
                updateDetailItems(DetailItem.ReviewText)
            }
            if (_uiState.value.movieReview.count() > 3) updateDetailItems(DetailItem.SeeAllReviewsButton)
        })
    }

    private fun insertMovieToWatchHistory(movie: MovieDetails?) {
        viewModelScope.launch {
            movie?.let { movieDetails ->
                insertMovieUseCase(
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

    private fun checkIfMovieRated(items: List<Rated>?, movie_id: Int) {
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
            wrapWithState({
                _uiState.update {
                    it.copy(
                        sessionIdResult = getSessionIdUseCase(),
                        isLoading = false
                    )
                }

                _uiState.update {
                    it.copy(
                        movieSetRatedResult = setRatingUseCase(
                            movie_id,
                            value,
                            _uiState.value.sessionIdResult ?: ""
                        )
                    )
                }
                if (_uiState.value.movieSetRatedResult.statusCode != null
                    && _uiState.value.movieSetRatedResult.statusCode == Constants.SUCCESS_REQUEST
                ) {
                    _check.postValue(value)
                }
                messageAppear.postValue(Event(true))
            })
        }
    }

    private fun updateDetailItems(item: DetailItem) {
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