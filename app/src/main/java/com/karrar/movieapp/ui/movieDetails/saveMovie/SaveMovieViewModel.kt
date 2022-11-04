package com.karrar.movieapp.ui.movieDetails.saveMovie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.mylist.GetMyListUseCase
import com.devfalah.usecases.mylist.SaveMovieToMyListUseCase
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.category.uiState.ErrorUIState
import com.karrar.movieapp.ui.movieDetails.saveMovie.uiState.MySavedListUIState
import com.karrar.movieapp.ui.movieDetails.saveMovie.uiState.SaveMovieUIEvent
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveMovieViewModel @Inject constructor(
    private val saveMovieToMyListUseCase: com.devfalah.usecases.mylist.SaveMovieToMyListUseCase,
    private val getMyListUseCase: com.devfalah.usecases.mylist.GetMyListUseCase,
    private val myListItemUIStateMapper: MyListItemUIStateMapper,
    state: SavedStateHandle,
) : BaseViewModel(), SaveListInteractionListener {

    private val args = SaveMovieDialogArgs.fromSavedStateHandle(state)

    private val _myListsUIState = MutableStateFlow(MySavedListUIState())
    val myListsUIState = _myListsUIState.asStateFlow()

    private val _saveMovieUIEvent = MutableStateFlow<Event<SaveMovieUIEvent?>>(Event(null))
    val saveMovieUIEvent = _saveMovieUIEvent.asStateFlow()

    init {
        getData()
    }

    override fun getData() {
        viewModelScope.launch {
            _myListsUIState.update { it.copy(isLoading = true, error = emptyList()) }
            try {
                _myListsUIState.update {
                    it.copy(
                        isLoading = false,
                        myListItemUI = getMyListUseCase().map { myListItemUIStateMapper.map(it) }
                    )
                }
            } catch (t: Throwable) {
                _myListsUIState.update {
                    it.copy(
                        isLoading = false,
                        error = listOf(ErrorUIState(404, t.message.toString()))
                    )
                }
            }
        }
    }

    override fun onClickSaveList(listID: Int) {
        viewModelScope.launch {
            val message = try {
                saveMovieToMyListUseCase(listID, args.movieId)
            } catch (t: Throwable) {
                t.message.toString()
            }
            _saveMovieUIEvent.update { Event(SaveMovieUIEvent.DisplayMessage(message ?: "")) }
        }
    }
}