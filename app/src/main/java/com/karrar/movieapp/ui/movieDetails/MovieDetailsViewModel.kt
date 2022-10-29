package com.karrar.movieapp.ui.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.domain.usecase.DeleteRatingUseCase
import com.karrar.movieapp.domain.usecase.GetMovieDetailsUseCase
import com.karrar.movieapp.domain.usecase.InsertMoviesUseCase
import com.karrar.movieapp.domain.usecase.SetRatingUseCase
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.movieDetails.mapper.*
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
    private val insertMoviesUseCase: InsertMoviesUseCase,
    private val setRatingUseCase: SetRatingUseCase,
    private val deleteRatingUseCase: DeleteRatingUseCase,
    private val movieDetailsUIStateMapper: MovieDetailsUIStateMapper,
    private val actorUIStateMapper: ActorUIStateMapper,
    private val mediaUIStateMapper: MediaUIStateMapper,
    private val ratedUIStateMapper: RatedUIStateMapper,
    private val reviewUIStateMapper: ReviewUIStateMapper,
    private val watchHistoryMapper: WatchHistoryMapper,
    state: SavedStateHandle,
) : BaseViewModel(), ActorsInteractionListener, MovieInteractionListener,
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

    private val _messageAppear = MutableLiveData<Boolean>()
    var messageAppear = _messageAppear.toLiveData()

    private val movieDetailItemsOfNestedView = mutableListOf<DetailItemUIState>()

    private val _uiState = MutableStateFlow(MovieUIState())
    val uiState: StateFlow<MovieUIState> = _uiState.asStateFlow()

    init {
        getData()
    }

    override fun getData() {
        getAllDetails(args.movieId)
    }


    private fun getAllDetails(movieId: Int) {
        _uiState.update { it.copy(isLoading = true, errorUIStates = emptyList()) }

        getMovieDetails(movieId)
        getMovieCast(movieId)
        getSimilarMovie(movieId)
        getRatedMovie(movieId)
        getMovieReviews(movieId)
    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val result = getMovieDetailsUseCase.getMovieDetails(movieId)
                _uiState.update {
                    it.copy(
                        movieDetailsResult = movieDetailsUIStateMapper.map(result),
                        isLoading = false,
                    )
                }
                onAddMovieDetailsItemOfNestedView(
                    DetailItemUIState.Header(_uiState.value.movieDetailsResult)
                )
                insertMovieToWatchHistory(_uiState.value.movieDetailsResult)

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        errorUIStates = onAddMessageToListError(e), isLoading = false
                    )
                }
            }
        }
    }

    private fun getMovieCast(movieId: Int) {
        viewModelScope.launch {
            try {
                val result = getMovieDetailsUseCase.getMovieCast(movieId)
                _uiState.update {
                    it.copy(
                        movieCastResult = result.map { actor -> actorUIStateMapper.map(actor) },
                        isLoading = false
                    )
                }
                onAddMovieDetailsItemOfNestedView(
                    DetailItemUIState.Cast(_uiState.value.movieCastResult)
                )
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorUIStates = onAddMessageToListError(e), isLoading = false)
                }
            }
        }
    }

    private fun getSimilarMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                val result = getMovieDetailsUseCase.getSimilarMovie(movieId)
                _uiState.update {
                    it.copy(
                        similarMoviesResult = result.map { media ->
                            mediaUIStateMapper.map(
                                media
                            )
                        }, isLoading = false
                    )
                }
                onAddMovieDetailsItemOfNestedView(
                    DetailItemUIState.SimilarMovies(_uiState.value.similarMoviesResult)
                )
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorUIStates = onAddMessageToListError(e), isLoading = false)
                }
            }
        }
    }

    private fun getRatedMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                val result = getMovieDetailsUseCase.getRatedMovie(0)
                _uiState.update {
                    it.copy(movieGetRatedResult = result.map { rated ->
                        ratedUIStateMapper.map(rated)
                    })
                }

                checkIfMovieRated(_uiState.value.movieGetRatedResult, movieId)
                onAddMovieDetailsItemOfNestedView(
                    DetailItemUIState.Rating(this@MovieDetailsViewModel)
                )
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorUIStates = onAddMessageToListError(e), isLoading = false)
                }
            }
        }
    }

    private fun getMovieReviews(movieId: Int) {
        viewModelScope.launch {
            try {
                val result = getMovieDetailsUseCase.getMovieReviews(movieId)
                _uiState.update {
                    it.copy(
                        movieReview = result.map { review -> reviewUIStateMapper.map(review) },
                        isLoading = false
                    )
                }

                getThreeCommits()
                onSeeAllReviews()
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorUIStates = onAddMessageToListError(e), isLoading = false)
                }
            }
        }
    }

    private fun getThreeCommits() {
        if (_uiState.value.movieReview.isNotEmpty()) {
            _uiState.value.movieReview.take(3)
                .forEach { onAddMovieDetailsItemOfNestedView(DetailItemUIState.Comment(it)) }
            onAddMovieDetailsItemOfNestedView(DetailItemUIState.ReviewText)
        }
    }

    private fun onSeeAllReviews() {
        if (_uiState.value.movieReview.count() > 3) onAddMovieDetailsItemOfNestedView(
            DetailItemUIState.SeeAllReviewsButton
        )
    }

    private fun insertMovieToWatchHistory(movie: MovieDetailsUIState?) {
        viewModelScope.launch {
            movie?.let { movieDetails ->
                val movieDetailsMapper = watchHistoryMapper.map(movieDetails)
                insertMoviesUseCase(movieDetailsMapper)
            }
        }
    }

    private fun checkIfMovieRated(items: List<RatedUIState>?, movieId: Int) {
        val item = items?.firstOrNull { it.id == movieId }
        item?.let { ratedUIState ->
            if (ratedUIState.rating != _uiState.value.ratingValue) {
                _uiState.update { it.copy(ratingValue = ratedUIState.rating) }
            }
        }
    }

    fun onChangeRating(value: Float) {
        _uiState.value.movieDetailsResult.let { onAddRating(it.id, value) }
    }

    private fun onAddRating(movieId: Int, value: Float) {
        viewModelScope.launch {
            try {
                if (value == 0f) {
                    deleteRatingUseCase(movieId)
                } else {
                    setRatingUseCase(movieId, value)
                }
                onShowMessageOfChangeRating()
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorUIStates = onAddMessageToListError(e), isLoading = false)
                }
            }
        }
    }

    private fun onShowMessageOfChangeRating() {
        _messageAppear.postValue(true)
    }

    private fun onAddMessageToListError(e: Exception): List<ErrorUIState> {
        return listOf(
            ErrorUIState(
                code = Constants.INTERNET_STATUS,
                message = e.message.toString()
            )
        )
    }

    private fun onAddMovieDetailsItemOfNestedView(item: DetailItemUIState) {
        movieDetailItemsOfNestedView.add(item)
        _uiState.update { it.copy(detailItemResult = movieDetailItemsOfNestedView) }
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