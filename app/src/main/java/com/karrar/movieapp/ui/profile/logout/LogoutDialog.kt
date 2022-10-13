package com.karrar.movieapp.ui.profile.logout

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.DialogLogoutBinding
import com.karrar.movieapp.ui.base.BaseDialog
import com.karrar.movieapp.utilities.EventObserve
import com.karrar.movieapp.utilities.observeEvent
import com.karrar.movieapp.utilities.setWidthPercent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoutDialog : BaseDialog<DialogLogoutBinding>() {
    override val layoutIdFragment: Int = R.layout.dialog_logout
    override val viewModel: LogoutViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setWidthPercent(90)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.clickLoginEvent.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(LogoutDialogDirections.actionLogoutDialogToHomeFragment())

        }

        viewModel.closeDialogEvent.observeEvent(viewLifecycleOwner) {
            dismiss()
        }
    }
}