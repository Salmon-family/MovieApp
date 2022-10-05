package com.karrar.movieapp.ui.logout

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.DialogLogoutBinding
import com.karrar.movieapp.ui.base.BaseDialogFragment
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoutDialog : BaseDialogFragment<DialogLogoutBinding>() {
    override val layoutIdFragment: Int = R.layout.dialog_logout
    override val viewModel: LogoutViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        observeEvents()
    }

    private fun observeEvents(){
        viewModel.navigateToLogin.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(R.id.action_logoutDialog_to_loginFragment)
        })

        viewModel.closeDialog.observe(viewLifecycleOwner, EventObserve {
            dismiss()
        })
    }
}