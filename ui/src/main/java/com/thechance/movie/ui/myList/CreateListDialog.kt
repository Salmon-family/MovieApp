package com.thechance.movie.ui.myList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.thechance.movie.R
import com.thechance.movie.databinding.FragmentCreateListDialogBinding
import com.thechance.movie.ui.base.BaseDialogFragment
import com.thechance.movie.ui.myList.myListUIState.MyListUIEvent
import com.thechance.movie.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateListDialog : BaseDialogFragment<FragmentCreateListDialogBinding>() {

    override val layoutIdFragment = R.layout.fragment_create_list_dialog
    override val viewModel: MyListsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectLast(viewModel.myListUIEvent) {
            it.peekContent()?.let {
                if (it is MyListUIEvent.CLickAddEvent) {
                    dismissDialog()
                }
            }
        }
    }

    private fun dismissDialog() {
        this.dismiss()
    }

}