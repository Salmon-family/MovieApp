package com.thechance.ui.profile.myratings

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.profile.myratings.RatedMoviesInteractionListener
import com.thechance.viewmodel.profile.myratings.RatedUIState

class RatedMoviesAdapter(items: List<RatedUIState>, listener: RatedMoviesInteractionListener) :
    BaseAdapter<RatedUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_rated_movie
}

