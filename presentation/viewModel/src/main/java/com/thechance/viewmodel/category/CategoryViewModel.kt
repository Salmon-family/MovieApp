package com.thechance.viewmodel.category

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.map
import com.thechance.ui.utilities.Constants.FIRST_CATEGORY_ID
import com.thechance.ui.utilities.Event
import com.thechance.viewmodel.BaseViewModel
import com.thechance.viewmodel.allMedia.MediaInteractionListener
import com.thechance.viewmodel.category.uiState.CategoryUIEvent
import com.thechance.viewmodel.category.uiState.CategoryUIState
import com.thechance.viewmodel.category.uiState.ErrorUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoryUseCase: com.devfalah.usecases.GetMediaByGenreIDUseCase,
    private val mediaUIStateMapper: MediaUIStateMapper,
    private val genreUIStateMapper: GenreUIStateMapper,
    private val getGenresUseCase: com.devfalah.usecases.GetGenreListUseCase,
) : BaseViewModel(), MediaInteractionListener, CategoryInteractionListener {

    private val _uiState = MutableStateFlow(CategoryUIState())
    val uiState: StateFlow<CategoryUIState> = _uiState.asStateFlow()

    private val _categoryUIEvent: MutableStateFlow<Event<CategoryUIEvent>> =
        MutableStateFlow(Event(CategoryUIEvent.SelectedCategory(FIRST_CATEGORY_ID)))
    val categoryUIEvent = _categoryUIEvent.asStateFlow()

    private var mediaId = -1

    fun setMedia(mediaId: Int) {
        this.mediaId = mediaId
        getData()
    }

    override fun getData() {
        _uiState.update { it.copy(isLoading = true) }
        getMediaList(uiState.value.selectedCategoryID)
        getGenre()
        _categoryUIEvent.update { Event(CategoryUIEvent.RetryEvent) }
    }

    private fun getGenre() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        genre = getGenresUseCase(mediaId).map { genreUIStateMapper.map(it) })
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = listOf(ErrorUIState(-1, t.message.toString()))) }
            }
        }
    }

    fun getMediaList(selectedCategory: Int) {
        viewModelScope.launch {
            val result = getCategoryUseCase(mediaId, selectedCategory)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    media = result.map { pagingData -> pagingData.map { mediaUIStateMapper.map(it) } },
                    error = emptyList()
                )
            }
        }
    }

    override fun onClickMedia(mediaId: Int) {
        _categoryUIEvent.update { Event(CategoryUIEvent.ClickMovieEvent(mediaId)) }
    }

    override fun onClickCategory(categoryId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedCategoryID = categoryId) }
            _categoryUIEvent.emit(Event(CategoryUIEvent.SelectedCategory(categoryId)))
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
                    it.copy(isLoading = false, error = emptyList())
                }
            }
            is LoadState.Error -> {
                _uiState.update {
                    it.copy(isLoading = false, error = listOf(ErrorUIState(404, "Error")))
                }
            }
        }
    }

}

