package com.karrar.movieapp.ui.login

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentLoginBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModels()
    override fun onStart() {
        super.onStart()
        observeEvents()
    }


    private fun observeEvents() {
        viewModel.loginEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        })

        viewModel.signUpEvent.observe(viewLifecycleOwner, EventObserve {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.TMDB_SIGNUP_URL))
            startActivity(browserIntent)
        })
    }
}