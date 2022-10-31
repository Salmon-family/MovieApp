package com.karrar.movieapp.ui.tvShowDetails.episodes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.thechance.repository.SeriesRepository
import com.karrar.movieapp.domain.models.Season
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val seriesRepository: com.thechance.repository.SeriesRepository,
    state: SavedStateHandle
) : BaseViewModel(), EpisodesInteractionListener {

    private val args = EpisodesFragmentArgs.fromSavedStateHandle(state)

    private var _seasonDetails = MutableLiveData<UIState<Season>>()
    val seasonDetails = _seasonDetails.toLiveData()


    init {
        getData()
    }

    override fun getData() {
//        _seasonDetails.postValue(UIState.Loading)
//        wrapWithState({
//            val response = seriesRepository.getSeasonDetails(args.tvShowId, args.seasonNumber)
//            _seasonDetails.postValue(UIState.Success(response))
//        }, {
//            _seasonDetails.postValue(UIState.Error("error"))
//        })
    }


}