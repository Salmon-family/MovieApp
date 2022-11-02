package com.karrar.movieapp.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.map
import com.karrar.movieapp.domain.usecases.GetGenreListUseCase
import com.karrar.movieapp.domain.usecases.GetMediaByGenreIDUseCase
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.category.uiState.CategoryUIState
import com.karrar.movieapp.ui.category.uiState.ErrorUIState
import com.karrar.movieapp.utilities.Constants.FIRST_CATEGORY_ID
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoryUseCase: GetMediaByGenreIDUseCase,
    private val mediaUIStateMapper: MediaUIStateMapper,
    private val genreUIStateMapper: GenreUIStateMapper,
    private val getGenresUseCase: GetGenreListUseCase,
    state: SavedStateHandle
) : BaseViewModel(), MediaInteractionListener, CategoryInteractionListener {

    val args = CategoryFragmentArgs.fromSavedStateHandle(state)

    private val _uiState = MutableStateFlow(CategoryUIState())
    val uiState: StateFlow<CategoryUIState> = _uiState.asStateFlow()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    var clickMovieEvent = _clickMovieEvent

    private val _clickRetryEvent = MutableLiveData<Event<Boolean>>()
    val clickRetryEvent = _clickRetryEvent.toLiveData()

    private val _selectedCategory = MutableLiveData(FIRST_CATEGORY_ID)
    val selectedCategory = _selectedCategory.toLiveData()

    init {
        getData()
    }

    override fun getData() {
        _uiState.update { it.copy(isLoading = true) }
        getMediaList()
        getGenre()
        _clickRetryEvent.postEvent(true)
    }

    private fun getGenre() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        genre = getGenresUseCase(args.mediaId).map { genreUIStateMapper.map(it) })
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = listOf(ErrorUIState(-1, t.message.toString()))) }
            }
        }
    }

    fun getMediaList() {
        viewModelScope.launch {
            val result =
                getCategoryUseCase(args.mediaId, selectedCategory.value ?: FIRST_CATEGORY_ID)
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
        _clickMovieEvent.postValue(Event(mediaId))
    }

    override fun onClickCategory(categoryId: Int) {
        _selectedCategory.postValue(categoryId)
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

