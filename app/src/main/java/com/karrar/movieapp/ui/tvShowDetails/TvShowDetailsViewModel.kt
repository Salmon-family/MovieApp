package com.karrar.movieapp.ui.tvShowDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.models.TvShowDetails
import com.karrar.movieapp.domain.usecase.tvShowDetails.GetTvShowDetailsUseCase
import com.karrar.movieapp.domain.usecase.tvShowDetails.InsertTvShowUserCase
import com.karrar.movieapp.domain.usecase.tvShowDetails.SetRatingUesCase
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.movieDetails.DetailInteractionListener
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper.TvShowMapperContainer
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.*
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
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
    state: SavedStateHandle
) : BaseViewModel(),
    ActorsInteractionListener,
    SeasonInteractionListener,
    DetailInteractionListener {

    val args = TvShowDetailsFragmentArgs.fromSavedStateHandle(state)

    private var _tvShowDetails = MutableLiveData<UIState<TvShowDetails>>()
    val tvShowDetails = _tvShowDetails.toLiveData()

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent = _clickBackEvent.toLiveData()

    private val _clickCastEvent = MutableLiveData<Event<Int>>()
    var clickCastEvent = _clickCastEvent.toLiveData()

    private val _clickPlayTrailerEvent = MutableLiveData<Event<Boolean>>()
    var clickPlayTrailerEvent = _clickPlayTrailerEvent.toLiveData()

    private val _clickReviewsEvent = MutableLiveData<Event<Boolean>>()
    var clickReviewsEvent = _clickReviewsEvent.toLiveData()

    private val _clickEpisodeEvent = MutableLiveData<Event<Int>>()
    val clickEpisodeEvent = _clickEpisodeEvent.toLiveData()

    private val _messageAppear = MutableLiveData<Boolean>()
    var messageAppear = _messageAppear.toLiveData()

    private val movieDetailItemsOfNestedView = mutableListOf<DetailItemUIState>()

    private val _stateFlow = MutableStateFlow(TvShowDetailsUIState())
    val stateFlow: StateFlow<TvShowDetailsUIState> = _stateFlow.asStateFlow()

    init {
        getData()
    }

    override fun getData() {
            getAllDetails(args.tvShowId)

    }

    private  fun getAllDetails(tvShowId: Int) {
        _stateFlow.update { it.copy(isLoading = true, errorUIState = emptyList()) }
        getTvShowDetails(tvShowId)
        getTvShowCast(tvShowId)
        getSeasons(tvShowId)
        getRatedTvShows(tvShowId)
        getTvShowReviews(tvShowId)
    }

    private  fun getTvShowDetails(tvShowId: Int) {
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

    private  fun getTvShowCast(tvShowId: Int) {
        viewModelScope.launch{
            try {
                val result = getTvShowDetailsUseCase.getSeriesCast(tvShowId)
                _stateFlow.update { it ->
                    it.copy(
                        seriesCastResult = result.map { tvShowMapperContainer.tvShowCastUIMapper.map(it) },
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

    private  fun getSeasons(tvShowId: Int) {
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

    private  fun getRatedTvShows(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val result = getTvShowDetailsUseCase.getTvShowRated(0).map(tvShowMapperContainer.tvShowRatedUIMapper::map)
                checkIfTvShowRated(result,tvShowId)
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

    private  fun getTvShowReviews(tvShowId: Int) {
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
        _stateFlow.value.tvShowDetailsResult.let { onAddRating(it.tvShowId, value) }
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
        _messageAppear.postValue(true)
    }

    private fun updateDetailItems(items: DetailItemUIState) {
        movieDetailItemsOfNestedView.add(items)
        _stateFlow.update { it.copy(detailItemResult = movieDetailItemsOfNestedView) }
    }

    override fun onClickSave() {}

    override fun onClickPlayTrailer() {
        _clickPlayTrailerEvent.postValue(Event(true))
    }

    override fun onclickBack() {
        _clickBackEvent.postValue(Event(true))
    }

    override fun onclickViewReviews() {
        _clickReviewsEvent.postValue(Event(true))
    }

    override fun onClickActor(actorID: Int) {
        _clickCastEvent.postValue(Event(actorID))
    }

    override fun onClickSeason(seasonNumber: Int) {
        _clickEpisodeEvent.postEvent(seasonNumber)
    }
}
