package com.thechance.viewmodel.allMedia

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.map
import com.devfalah.types.AllMediaType
import com.thechance.ui.utilities.Event
import com.thechance.viewmodel.BaseViewModel
import com.thechance.viewmodel.mappers.MediaUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMovieViewModel @Inject constructor(
    private val checkIfMediaIsSeriesUseCase: com.devfalah.usecases.allMedia.CheckIfMediaIsSeriesUseCase,
    private val getMediaByType: com.devfalah.usecases.allMedia.GetMediaByTypeUseCase,
    private val mediaUiMapper: MediaUiMapper,
) : BaseViewModel(), MediaInteractionListener {

    private val _uiState = MutableStateFlow(AllMediaUiState())
    val uiState = _uiState.asStateFlow()

    private val _mediaUIEvent: MutableStateFlow<Event<MediaUIEvent>?> = MutableStateFlow(null)
    val mediaUIEvent = _mediaUIEvent.asStateFlow()
    private var id = -1
    private lateinit var type: com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.AllMediaType

    fun setMedia(id: Int, type: com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.AllMediaType) {
        this.id = id
        this.type = type
        getData()
    }

    override fun getData() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val allMediaItems =
                getMediaByType(type.name, id).map { pager -> pager.map { mediaUiMapper.map(it) } }
            _uiState.update { it.copy(allMedia = allMediaItems, isLoading = false) }
        }

        _mediaUIEvent.update { Event(MediaUIEvent.RetryEvent) }
    }

    override fun onClickMedia(mediaId: Int) {
        if (checkIfMediaIsSeriesUseCase(type.name)) {
            _mediaUIEvent.update { Event(MediaUIEvent.ClickSeriesEvent(mediaId)) }
        } else {
            _mediaUIEvent.update { Event(MediaUIEvent.ClickMovieEvent(mediaId)) }
        }
    }

    fun setErrorUiState(combinedLoadStates: CombinedLoadStates) {
        when (combinedLoadStates.refresh) {
            is LoadState.NotLoading -> {
                _uiState.update {
                    it.copy(isLoading = false, error = emptyList())
                }
            }
            LoadState.Loading -> {
                _uiState.update {
                    it.copy(isLoading = true, error = emptyList())
                }
            }
            is LoadState.Error -> {
                _uiState.update {
                    it.copy(isLoading = false, error = listOf(Error(404, "")))
                }
            }
        }

    }
}