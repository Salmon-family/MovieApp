package com.karrar.movieapp.ui.myList

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentCreateListDialogBinding
import com.karrar.movieapp.ui.base.BaseDialogFragment
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateListDialog : BaseDialogFragment<FragmentCreateListDialogBinding>() {
    override val layoutIdFragment = R.layout.fragment_create_list_dialog
    override val viewModel: MyListsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.onClickAddEvent.observeEvent(viewLifecycleOwner) {
            this.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        setFragmentResult(Constants.REQUEST_KEY, bundleOf(Constants.BUNDLE_KEY to true))
    }

}