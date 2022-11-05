package com.thechance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun wrapWithState(function: suspend () -> Unit, errorFunction: (e: Throwable) -> Unit = {}) {
        viewModelScope.launch {
            try {
                function()
            } catch (e: Throwable) {
                errorFunction(e)
            }
        }
    }

    fun <T> collectData(
        data: Flow<List<T>>,
        function: suspend (List<T>) -> Unit
    ){
        viewModelScope.launch(Dispatchers.IO) {
            data.collect{
                function(it)
            }

        }
    }

    abstract fun getData()

}