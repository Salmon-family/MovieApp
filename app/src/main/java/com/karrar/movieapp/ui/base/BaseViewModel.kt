package com.karrar.movieapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.ui.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

abstract class BaseViewModel:ViewModel() {

    val config = PagingConfig(pageSize = 100, prefetchDistance = 3 , enablePlaceholders = true)

    fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO)
                .collect { state ->
                    function(state)
                }
        }
    }

    fun <I> wrapWithUIState(function: suspend () -> I?, data: MutableLiveData<UIState<I>>) {
        viewModelScope.launch {
            val items = function()
            if (items == null) {
                data.postValue(UIState.Error)
            } else {
                data.postValue(UIState.Success(items))
            }
        }
    }
}