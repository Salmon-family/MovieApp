package com.thechance.viewmodel.base

import androidx.lifecycle.MutableLiveData
import com.thechance.viewmodel.BaseViewModel

abstract class MediaDetailsViewModel : BaseViewModel() {

    abstract var ratingValue: MutableLiveData<Float>

}
