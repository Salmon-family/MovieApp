package com.karrar.movieapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karrar.movieapp.utilities.toLiveData

class SharedViewModel : ViewModel() {

    private val _toolbarVisibility = MutableLiveData(false)
    val toolbarVisibility = _toolbarVisibility.toLiveData()

    private val _toolbarTransparent = MutableLiveData(false)
    val toolbarTransparent = _toolbarTransparent.toLiveData()

    private val _toolbarTitle = MutableLiveData("")
    val toolbarTitle = _toolbarTitle.toLiveData()

    fun setToolbar(isVisible: Boolean, isTransparent: Boolean, title: String?) {
        _toolbarVisibility.postValue(isVisible)
        _toolbarTransparent.postValue(isTransparent)
        title?.let {
            _toolbarTitle.postValue(title)
        }
    }
}