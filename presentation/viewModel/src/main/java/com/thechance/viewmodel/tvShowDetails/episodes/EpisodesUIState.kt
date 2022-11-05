package com.thechance.viewmodel.tvShowDetails.episodes

import com.thechance.viewmodel.tvShowDetails.tvShowUIState.Error


data class EpisodesUIState(
    val seriesEpisodeUIState: List<EpisodeUIState> = listOf(),
    val isLoading: Boolean = false,
    val error: List<Error> = emptyList()
)
