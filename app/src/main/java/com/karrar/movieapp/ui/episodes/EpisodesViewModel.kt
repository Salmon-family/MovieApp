package com.karrar.movieapp.ui.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.models.Season
import com.karrar.movieapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val seriesRepository: SeriesRepository,
    state: SavedStateHandle
) : BaseViewModel(), EpisodesInteractionListener {

    private val args = EpisodesFragmentArgs.fromSavedStateHandle(state)

    private var _seasonDetails = MutableLiveData<State<Season>>()
    val seasonDetails: LiveData<State<Season>> = _seasonDetails


    init {
        getAllDetails(args.tvShowId, args.seasonNumber)
    }

    private fun getAllDetails(tvShowId: Int, seasonNumber: Int) {
        collectResponse(seriesRepository.getSeasonDetails(tvShowId, seasonNumber)) {
            _seasonDetails.postValue(it)
        }

    }

}