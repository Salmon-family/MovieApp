package com.karrar.movieapp.ui.profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentProfileBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.navigateToRatedMovies.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(R.id.action_profileFragment_to_ratedMoviesFragment)
        })

        viewModel.navigateToDialogLogout.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(R.id.action_profileFragment_to_logoutDialog)
        })

        viewModel.navigateToWatchHistory.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(R.id.action_profileFragment_to_watchHistoryFragment)
        })

    }
}