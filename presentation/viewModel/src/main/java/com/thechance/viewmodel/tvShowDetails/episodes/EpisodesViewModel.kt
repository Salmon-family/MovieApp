package com.thechance.viewmodel.tvShowDetails.episodes

import androidx.lifecycle.viewModelScope
import com.thechance.viewmodel.BaseViewModel
import com.thechance.viewmodel.category.com.thechance.viewmodel.tvShowDetails.episodes.EpisodesInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.thechance.viewmodel.tvShowDetails.tvShowUIState.Error

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val getSeasonsEpisodesUseCase: com.devfalah.usecases.tvShowDetails.GetSeasonsEpisodesUseCase,
    private val tvShowEpisodesUIMapper: TvShowEpisodesUIMapper,
) : BaseViewModel(), EpisodesInteractionListener {

    private val _stateFlowEpisode = MutableStateFlow(EpisodesUIState())
    val stateFlowEpisode: StateFlow<EpisodesUIState> = _stateFlowEpisode.asStateFlow()

    private var tvShowId = -1

    fun setTVShowData(tvShowID: Int) {
        this.tvShowId = tvShowID
        getData()
    }

    override fun getData() {
        viewModelScope.launch {
            _stateFlowEpisode.update { it.copy(isLoading = true) }
            try {
                val result =
                    getSeasonsEpisodesUseCase(tvShowId)
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
