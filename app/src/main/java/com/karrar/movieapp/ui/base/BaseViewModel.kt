package com.karrar.movieapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import com.karrar.movieapp.data.remote.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)

    fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO)
                .collect { state ->
                    function(state)
                }
        }
    }

    fun wrapWithState(function: suspend () -> Unit, errorFunction: (e: Throwable) -> Unit = {}) {
        viewModelScope.launch {
            try {
                function()
            } catch (e: Throwable) {
                errorFunction(e)
            }
        }

    }

}