package com.thechance.viewmodel.allMedia

import com.thechance.viewmodel.base.BaseInteractionListener


interface MediaInteractionListener : BaseInteractionListener {
    fun onClickMedia(mediaId: Int)
}