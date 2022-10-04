package com.karrar.movieapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    val toolbarVisibility = MutableLiveData(false)
    val toolbarTitle = MutableLiveData("")
}