package com.karrar.movieapp.ui.saved

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.CreateListDialogBinding
import com.karrar.movieapp.ui.base.BaseDialogFragment
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateListDialog() : BaseDialogFragment<CreateListDialogBinding>() {
    override val layoutIdFragment = R.layout.create_list_dialog
    override val viewModel: CreateListDialogViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.onClickAddEvent.observe(this, EventObserve {
            if (it) {
               dismiss()
            }
        })
    }
}