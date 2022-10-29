package com.karrar.movieapp.ui.tvShowDetails.episodes

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.usecase.tvShowDetails.GetTvShowDetailsUseCase
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper.TvShowMapperContainer
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.Error
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val getTvShowDetailsUseCase: GetTvShowDetailsUseCase,
    private val getTvShowMapperContainer: TvShowMapperContainer,
    state: SavedStateHandle
) : BaseViewModel(), EpisodesInteractionListener {

    private val args = EpisodesFragmentArgs.fromSavedStateHandle(state)

    private val _stateFlowEpisode = MutableStateFlow(EpisodesUIState())
    val stateFlowEpisode: StateFlow<EpisodesUIState> = _stateFlowEpisode.asStateFlow()

    init {
        getData()
    }

    override fun getData() {
        viewModelScope.launch {
            _stateFlowEpisode.update { it.copy(isLoading = true) }
            try {
                val result =
                    getTvShowDetailsUseCase.getSeasonsDetails(args.tvShowId, args.seasonNumber)
                Log.i("test", "getData: $result")
                _stateFlowEpisode.update { it ->
                    it.copy(
                        seriesEpisodeUIState = result.map { getTvShowMapperContainer.tvShowEpisodesUIMapper.map(it) },
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _stateFlowEpisode.update {
                    it.copy(
                        error = listOf(Error(message = e.message.toString())),
                        isLoading = false
                    )
                }
            }
        }
    }
}
