package com.karrar.movieapp.ui.profile.login

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.DialogLoginBinding
import com.karrar.movieapp.ui.base.BaseDialog
import com.karrar.movieapp.utilities.EventObserve
import com.karrar.movieapp.utilities.setWidthPercent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginDialog: BaseDialog<DialogLoginBinding>() {
    override val layoutIdFragment = R.layout.dialog_login
    override val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setWidthPercent(90)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.clickLogin.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(R.id.action_loginDialog_to_loginFragment3)
        })
    }
}