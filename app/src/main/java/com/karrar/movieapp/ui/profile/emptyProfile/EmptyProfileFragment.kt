package com.karrar.movieapp.ui.profile.emptyProfile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentEmptyProfileBinding
import com.karrar.movieapp.ui.base.BaseDialog
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmptyProfileFragment: BaseDialog<FragmentEmptyProfileBinding>() {
    override val layoutIdFragment = R.layout.fragment_empty_profile
    override val viewModel: EmptyProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.clickLogin.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(R.id.action_emptyProfileFragment_to_loginFragment)
        })
    }
}