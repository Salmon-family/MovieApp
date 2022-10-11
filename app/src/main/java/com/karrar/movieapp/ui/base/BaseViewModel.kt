package com.karrar.movieapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.ui.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

abstract class BaseViewModel:ViewModel() {

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