package com.thechance.viewmodel.category.com.thechance.viewmodel.tvShowDetails.episodes

import com.thechance.viewmodel.base.BaseInteractionListener

interface SeasonInteractionListener : BaseInteractionListener {
    fun onClickSeason(seasonNumber: Int)
}