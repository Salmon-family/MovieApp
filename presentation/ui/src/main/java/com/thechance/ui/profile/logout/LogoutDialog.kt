package com.thechance.ui.profile.logout

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thechance.ui.R
import com.thechance.ui.base.BaseDialog
import com.thechance.ui.databinding.DialogLogoutBinding
import com.thechance.ui.utilities.setWidthPercent
import com.thechance.viewmodel.profile.logout.LogoutUIEvent
import com.thechance.viewmodel.profile.logout.LogoutViewModel
import com.thechance.viewmodel.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoutDialog : BaseDialog<DialogLogoutBinding>() {
    override val layoutIdFragment: Int = R.layout.dialog_logout
    override val viewModel: LogoutViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setWidthPercent(90)
        collectLast(viewModel.logoutUIEvent) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: LogoutUIEvent) {
        when (event) {
            LogoutUIEvent.CloseDialogEvent -> {
                dismiss()
            }
            LogoutUIEvent.LogoutEvent -> {
                findNavController().navigate(R.id.action_logoutDialog_to_homeFragment)
            }
        }
    }

}