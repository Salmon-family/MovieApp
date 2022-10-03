package com.karrar.movieapp.ui.category.temp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentTempBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve

class TempFragment : BaseFragment<FragmentTempBinding>() {

    override val layoutIdFragment = R.layout.fragment_temp
    override val viewModel: TempViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
    }

    private fun observeEvents() {
        viewModel.moviesEvent.observe(viewLifecycleOwner, EventObserve { navigateToMedia(it) })
        viewModel.tvEvent.observe(viewLifecycleOwner, EventObserve { navigateToMedia(it) })
    }

    private fun navigateToMedia(id: Int) {
        val action = TempFragmentDirections.actionTempFragmentToCategoryFragment(id)
        findNavController().navigate(action)
    }

}