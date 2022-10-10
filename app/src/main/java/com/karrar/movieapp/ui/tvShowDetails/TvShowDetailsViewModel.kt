package com.karrar.movieapp.ui.tvShowDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.movie.RatedMovie
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.SeasonMapper
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.models.Review
import com.karrar.movieapp.domain.models.Season
import com.karrar.movieapp.domain.models.TvShowDetails
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.movieDetails.DetailInteractionListener
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val seasonMapper: SeasonMapper,
    state: SavedStateHandle
) : BaseViewModel(), ActorsInteractionListener, SeasonInteractionListener,
    DetailInteractionListener {

    val args = TvShowDetailsFragmentArgs.fromSavedStateHandle(state)

    private var _tvShowDetails = MutableLiveData<State<TvShowDetails>>()
    val tvShowDetails: LiveData<State<TvShowDetails>> = _tvShowDetails

    private var _tvShowCast = MutableLiveData<State<List<Actor>>>()
    val tvShowCast: LiveData<State<List<Actor>>> = _tvShowCast

    private var _tvShowReviews = MutableLiveData<State<List<Review>>>()
    val tvShowReviews: LiveData<State<List<Review>>> = _tvShowReviews

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    private val _clickCastEvent = MutableLiveData<Event<Int>>()
    var clickCastEvent: LiveData<Event<Int>> = _clickCastEvent

    private val _clickPlayTrailerEvent = MutableLiveData<Event<Int>>()
    var clickPlayTrailerEvent: LiveData<Event<Int>> = _clickPlayTrailerEvent

    private val _clickReviewsEvent = MutableLiveData<Event<Int>>()
    var clickReviewsEvent: LiveData<Event<Int>> = _clickReviewsEvent

    private val _clickSaveEvent = MutableLiveData<Event<Int>>()
    var clickSaveEvent: LiveData<Event<Int>> = _clickSaveEvent

    private val _clickEpisodeEvent = MutableLiveData<Event<Int>>()
    val clickEpisodeEvent: LiveData<Event<Int>> = _clickEpisodeEvent

    private val _check = MutableLiveData<Float?>()

    val messageAppear = MutableLiveData(Event(false))

    var ratingValue = MutableLiveData<Float?>()

    init {
        getAllDetails(args.tvShowId)
    }

    private fun getAllDetails(tvShowId: Int) {

        collectResponse(seriesRepository.getTvShowDetails(tvShowId)) { _tvShowDetails.postValue(it) }

        collectResponse(seriesRepository.getTvShowCast(tvShowId)) { _tvShowCast.postValue(it) }

        collectResponse(seriesRepository.getTvShowReviews(tvShowId)) { _tvShowReviews.postValue(it) }

        collectResponse(seriesRepository.getRatedTvShow(14012083, SESSION_ID)) {
            checkIfTvShowRated(it.toData()?.items, tvShowId)
        }
    }

    private fun checkIfTvShowRated(items: List<RatedMovie>?, tvShowId: Int) {
        val item = items?.firstOrNull { it.id == tvShowId }
        item?.let {
            if (it.rating != ratingValue.value) {
                _check.postValue(it.rating)
                ratingValue.postValue(it.rating)
            }
        }
    }

    fun onAddRating(value: Float) {
        if (_check.value != value) {
            collectResponse(seriesRepository.setRating(args.tvShowId, value, SESSION_ID)) {
                if (it is State.Success) {
                    messageAppear.postValue(Event(true))
                    _check.postValue(value)
                }
            }
        }
    }


    override fun onClickSave() {
        _clickSaveEvent.postValue(Event(args.tvShowId))
    }

    override fun onClickPlayTrailer() {
        _clickPlayTrailerEvent.postValue(Event(args.tvShowId))
    }

    override fun onclickBack() {
        _clickBackEvent.postValue(Event(true))
    }

    override fun onclickViewReviews() {
        _clickReviewsEvent.postValue(Event(args.tvShowId))
    }

    override fun onClickActor(actorID: Int) {
        _clickCastEvent.postValue(Event(actorID))
    }

    override fun onClickSeason(seasonNumber: Int) {
        _clickEpisodeEvent.postEvent(seasonNumber)
    }


    companion object {
        const val SESSION_ID = "1d92e6a329c67e2e5e0486a0a93d5980711535b1"
    }

}