package com.karrar.movieapp.ui.tvShowDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.usecase.tvShowDetails.GetTvShowDetailsUseCase
import com.karrar.movieapp.domain.usecase.tvShowDetails.InsertTvShowUserCase
import com.karrar.movieapp.domain.usecase.tvShowDetails.SetRatingUesCase
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.movieDetails.DetailInteractionListener
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper.TvShowMapperContainer
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.*
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val getTvShowDetailsUseCase: GetTvShowDetailsUseCase,
    private val getInsertTvShowUserCase: InsertTvShowUserCase,
    private val setRatingUesCase: SetRatingUesCase,
    private val tvShowMapperContainer: TvShowMapperContainer,
    state: SavedStateHandle,
) : BaseViewModel(),
    ActorsInteractionListener,
    SeasonInteractionListener,
    DetailInteractionListener {

    val args = TvShowDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _tvShowDetailsUIEvent =
        MutableStateFlow<Event<TvShowDetailsUIEvent>>(Event(TvShowDetailsUIEvent.InitialEvent))
    val tvShowDetailsUIEvent = _tvShowDetailsUIEvent.asStateFlow()


    private val movieDetailItemsOfNestedView = mutableListOf<DetailItemUIState>()

    private val _stateFlow = MutableStateFlow(TvShowDetailsUIState())
    val stateFlow: StateFlow<TvShowDetailsUIState> = _stateFlow.asStateFlow()

    init {
        getData()
    }

    override fun getData() {
        getAllDetails(args.tvShowId)

    }

    private fun getAllDetails(tvShowId: Int) {
        _stateFlow.update { it.copy(isLoading = true, errorUIState = emptyList()) }
        getTvShowDetails(tvShowId)
        getTvShowCast(tvShowId)
        getSeasons(tvShowId)
        getRatedTvShows(tvShowId)
        getTvShowReviews(tvShowId)
    }

    private fun getTvShowDetails(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val result = getTvShowDetailsUseCase.getTvShowDetails(tvShowId)
                val tvShowDetailsResult = tvShowMapperContainer.tvShowDetailsUIMapper.map(result)
                _stateFlow.update {
                    it.copy(
                        tvShowDetailsResult = tvShowDetailsResult,
                        isLoading = false
                    )
                }

                updateDetailItems(DetailItemUIState.Header(_stateFlow.value.tvShowDetailsResult))
                insertMovieToWatchHistory(tvShowDetailsResult)
            } catch (e: Exception) {
                _stateFlow.update {
                    it.copy(
                        errorUIState = onAddMessageToListError(e),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun getTvShowCast(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val result = getTvShowDetailsUseCase.getSeriesCast(tvShowId)
                _stateFlow.update { it ->
                    it.copy(
                        seriesCastResult = result.map {
                            tvShowMapperContainer.tvShowCastUIMapper.map(it)
                        },
                        isLoading = false
                    )
                }
                updateDetailItems(DetailItemUIState.Cast(_stateFlow.value.seriesCastResult))
            } catch (e: Exception) {
                _stateFlow.update {
                    it.copy(
                        errorUIState = onAddMessageToListError(e),
                        isLoading = false
                    )
                }
            }

        }
    }

    private fun getSeasons(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val seasons = getTvShowDetailsUseCase.getSeasons(tvShowId)
                _stateFlow.update { it ->
                    it.copy(
                        seriesSeasonsResult = seasons.map {
                            tvShowMapperContainer.tvShowSeasonUIMapper.map(it)
                        },
                        isLoading = false
                    )
                }
                updateDetailItems(DetailItemUIState.Seasons(_stateFlow.value.seriesSeasonsResult))
            } catch (e: Exception) {
                _stateFlow.update {
                    it.copy(
                        errorUIState = onAddMessageToListError(e),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun getRatedTvShows(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val result = getTvShowDetailsUseCase.getTvShowRated(0)
                    .map(tvShowMapperContainer.tvShowRatedUIMapper::map)
                checkIfTvShowRated(result, tvShowId)
                updateDetailItems(DetailItemUIState.Rating(this@TvShowDetailsViewModel))
            } catch (e: Exception) {
                _stateFlow.update {
                    it.copy(
                        errorUIState = onAddMessageToListError(e),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun getTvShowReviews(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val result = getTvShowDetailsUseCase.getTvShowReviews(tvShowId)
                _stateFlow.update { it ->
                    it.copy(
                        seriesReviewsResult = result.map {
                            tvShowMapperContainer.tvShowReviewUIMapper.map(it)
                        },
                        isLoading = false
                    )
                }
                getThreeCommits()
                onSeeAllReviews()
            } catch (e: Exception) {
                _stateFlow.update {
                    it.copy(
                        errorUIState = onAddMessageToListError(e),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun getThreeCommits() {
        if (_stateFlow.value.seriesReviewsResult.isNotEmpty()) {
            _stateFlow.value.seriesReviewsResult.take(3)
                .forEach { updateDetailItems(DetailItemUIState.Comment(it)) }
            updateDetailItems(DetailItemUIState.ReviewText)
        }
    }

    private fun onSeeAllReviews() {
        if (_stateFlow.value.seriesReviewsResult.count() > 3) updateDetailItems(
            DetailItemUIState.SeeAllReviewsButton
        )
    }

    private fun insertMovieToWatchHistory(tvShow: TvShowDetailsResultUIState) {
        viewModelScope.launch {
            getInsertTvShowUserCase(tvShowMapperContainer.tvShowWatchHistoryMapper.map(tvShow))
        }
    }

    private fun checkIfTvShowRated(items: List<RatedUIState>?, tvShowId: Int) {
        val item = items?.firstOrNull { it.id == tvShowId }
        item?.let { ratedUIState ->
            if (ratedUIState.rating != _stateFlow.value.ratingValue) {
                _stateFlow.update { it.copy(ratingValue = ratedUIState.rating) }
            }
        }
    }

    fun onChangeRating(value: Float) {
        viewModelScope.launch {
            try {
                onAddRating(args.tvShowId, value)
                _stateFlow.value.tvShowDetailsResult.let { onAddRating(it.tvShowId, value) }
                _tvShowDetailsUIEvent.update { Event(TvShowDetailsUIEvent.MessageAppear) }
            } catch (e: Throwable) {
            }
        }
    }

    private fun onAddRating(tvShowId: Int, value: Float) {
        viewModelScope.launch {
            try {
                setRatingUesCase(tvShowId, value)
                onShowMessageOfChangeRating()
            } catch (e: Exception) {
                _stateFlow.update {
                    it.copy(
                        errorUIState = onAddMessageToListError(e),
                        isLoading = false
                    )
                }
            }
        }
    }


    private fun onAddMessageToListError(e: Exception): List<Error> {
        return listOf(
            Error(
                code = Constants.INTERNET_STATUS,
                message = e.message.toString()
            )
        )
    }

    private fun onShowMessageOfChangeRating() {
        _tvShowDetailsUIEvent.update { Event(TvShowDetailsUIEvent.MessageAppear) }
    }

    private fun updateDetailItems(items: DetailItemUIState) {
        movieDetailItemsOfNestedView.add(items)
        _stateFlow.update { it.copy(detailItemResult = movieDetailItemsOfNestedView) }
    }

    override fun onClickSave() {}

    override fun onClickPlayTrailer() {
        _tvShowDetailsUIEvent.update { Event(TvShowDetailsUIEvent.ClickPlayTrailerEvent) }
    }

    override fun onclickBack() {
        _tvShowDetailsUIEvent.update { Event(TvShowDetailsUIEvent.ClickBackEvent) }
    }

    override fun onclickViewReviews() {
        _tvShowDetailsUIEvent.update { Event(TvShowDetailsUIEvent.ClickReviewsEvent) }

    }

    override fun onClickActor(actorID: Int) {
        _tvShowDetailsUIEvent.update { Event(TvShowDetailsUIEvent.ClickCastEvent(actorID)) }
    }

    override fun onClickSeason(seasonNumber: Int) {
        _tvShowDetailsUIEvent.update { Event(TvShowDetailsUIEvent.ClickSeasonEvent(seasonNumber)) }
    }
}
