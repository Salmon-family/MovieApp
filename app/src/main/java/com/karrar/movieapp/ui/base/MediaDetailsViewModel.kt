package com.karrar.movieapp.ui.base

import androidx.lifecycle.MutableLiveData

abstract class MediaDetailsViewModel : BaseViewModel() {

    abstract var ratingValue: MutableLiveData<Float>

}
