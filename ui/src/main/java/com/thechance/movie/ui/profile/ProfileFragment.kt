package com.thechance.movie.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thechance.movie.R
import com.thechance.movie.databinding.FragmentProfileBinding
import com.thechance.movie.ui.base.BaseFragment
import com.thechance.movie.utilities.Constants
import com.thechance.movie.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.profile))

        collectLast(viewModel.profileUIEvent) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: ProfileUIEvent) {
        val action = when (event) {
            ProfileUIEvent.DialogLogoutEvent -> {
                ProfileFragmentDirections.actionProfileFragmentToLogoutDialog()
            }
            ProfileUIEvent.LoginEvent -> {
                ProfileFragmentDirections.actionProfileFragmentToLoginFragment(Constants.PROFILE)
            }
            ProfileUIEvent.RatedMoviesEvent -> {
                ProfileFragmentDirections.actionProfileFragmentToRatedMoviesFragment()
            }
            ProfileUIEvent.WatchHistoryEvent -> {
                ProfileFragmentDirections.actionProfileFragmentToWatchHistoryFragment()
            }
        }
        findNavController().navigate(action)
    }

}