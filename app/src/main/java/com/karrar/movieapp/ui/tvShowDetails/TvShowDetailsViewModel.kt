package com.karrar.movieapp.ui.tvShowDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.domain.models.TvShowDetails
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.MediaDetailsViewModel
import com.karrar.movieapp.ui.movieDetails.DetailInteractionListener
import com.karrar.movieapp.ui.movieDetails.DetailItem
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val accountRepository: AccountRepository,
    state: SavedStateHandle
) : MediaDetailsViewModel(), ActorsInteractionListener, SeasonInteractionListener,
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

    private val _check = MutableLiveData<Float?>()

    val messageAppear = MutableLiveData(Event(false))

    override var ratingValue = MutableLiveData<Float>()

    val detailItemsLiveData = MutableLiveData<UIState<List<DetailItem>>>()
    private val detailItems = mutableListOf<DetailItem>()


    init {
        getData()
    }

    override fun getData() {
        getAllDetails(args.tvShowId)
    }

    private fun getAllDetails(tvShowId: Int) {
        detailItemsLiveData.postValue(UIState.Loading)
        getTvShowDetails(tvShowId)
        getTvShowCast(tvShowId)
        getSeasons(tvShowId)
        getRatedTvShows(tvShowId)
        getTvShowReviews(tvShowId)
    }

    private fun getTvShowDetails(tvShowId: Int) {
        wrapWithState(
            {
                val response = seriesRepository.getTvShowDetails(tvShowId)
                updateDetailItems(DetailItem.Header(response))
                insertMovieToWatchHistory(response)
            },
            {
                detailItemsLiveData.postValue(UIState.Error("error"))
            })
    }

    private fun getTvShowCast(tvShowId: Int) {
        wrapWithState({
            val response = seriesRepository.getTvShowCast(tvShowId)
            updateDetailItems(DetailItem.Cast(response))
        })
    }

    private fun getSeasons(tvShowId: Int) {
        wrapWithState({
            val response = seriesRepository.getTvShowDetails(tvShowId).seasons
            updateDetailItems(DetailItem.Seasons(response))
        })
    }

    private fun getRatedTvShows(tvShowId: Int) {
//        viewModelScope.launch {
//            accountRepository.getSessionId().collectLatest {
//                wrapWithState({
//                    val response = seriesRepository.getRatedTvShow(0, it.toString())
//                    checkIfTvShowRated(response, tvShowId)
//                    updateDetailItems(DetailItem.Rating(this@TvShowDetailsViewModel))
//                })
//            }
//        }
    }

    private fun getTvShowReviews(tvShowId: Int) {
        wrapWithState({
            val response = seriesRepository.getTvShowReviews(tvShowId)
            if (response.isNotEmpty()) {
                response.take(3).forEach { updateDetailItems(DetailItem.Comment(it)) }
                updateDetailItems(DetailItem.ReviewText)
            }
            if (response.count() > 3) updateDetailItems(DetailItem.SeeAllReviewsButton)
        })
    }


    private fun updateDetailItems(item: DetailItem) {
        detailItems.add(item)
        detailItemsLiveData.postValue(UIState.Success(detailItems))
    }


    private fun insertMovieToWatchHistory(tvShow: TvShowDetails?) {
        viewModelScope.launch {
            tvShow?.let { tvShowDetails ->
                seriesRepository.insertTvShow(
                    WatchHistoryEntity(
                        id = tvShowDetails.id,
                        posterPath = tvShowDetails.image,
                        movieTitle = tvShowDetails.name,
                        movieDuration = tvShowDetails.specialNumber,
                        voteAverage = tvShowDetails.voteAverage,
                        releaseDate = tvShowDetails.releaseDate,
                        mediaType = Constants.TV_SHOWS
                    )
                )
            }
        }
    }

    private fun checkIfTvShowRated(items: List<Rated>?, tvShowId: Int) {
        val item = items?.firstOrNull { it.id == tvShowId }
        item?.let {
            if (it.rating != ratingValue.value) {
                _check.postValue(it.rating)
                ratingValue.postValue(it.rating)
            }
        }
    }

    fun onAddRating(tvShowId: Int, value: Float) {
//        if (_check.value != value) {
//            wrapWithState({
//                accountRepository.getSessionId().collect {
//                    val response = seriesRepository.setRating(tvShowId, value, it.toString())
//                    if (response.statusCode != null
//                        && response.statusCode == Constants.SUCCESS_REQUEST
//                    ) {
//                        _check.postValue(value)
//                    }
//                }
//            })
//            messageAppear.postValue(Event(true))
//        }
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