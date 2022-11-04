package com.karrar.movieapp.ui.tvShowDetails.episodes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.tvShowDetails.GetSeasonsEpisodesUseCase
import com.karrar.movieapp.ui.base.BaseViewModel
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
    private val getSeasonsEpisodesUseCase: com.devfalah.usecases.tvShowDetails.GetSeasonsEpisodesUseCase,
    private val tvShowEpisodesUIMapper: TvShowEpisodesUIMapper,
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
                    getSeasonsEpisodesUseCase(args.tvShowId)
                _stateFlowEpisode.update { it ->
                    it.copy(
                        seriesEpisodeUIState = result.map(tvShowEpisodesUIMapper::map),
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
