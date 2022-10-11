package com.karrar.movieapp.ui.profile

import android.os.Bundle
import android.view.View
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
        setTitle(true, getString(R.string.profile))
        checkHaveAccount()
    }

    private fun checkHaveAccount() {
        viewModel.sessionId.observe(viewLifecycleOwner) {
            if (it?.isEmpty() == true) {
                findNavController().navigate(R.id.action_profileFragment_to_emptyProfileFragment)
            }
        }
    }

    private fun observeEvents() {
        viewModel.clickRatedMoviesEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(R.id.action_profileFragment_to_ratedMoviesFragment)
        })

        viewModel.clickDialogLogoutEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(R.id.action_profileFragment_to_logoutDialog)
        })

        viewModel.clickWatchHistoryEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(R.id.action_profileFragment_to_watchHistoryFragment)
        })
    }
}