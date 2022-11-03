package com.karrar.movieapp.ui.login

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentLoginBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        setTitle(false)
        collectLast(viewModel.loginEvent) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.LoginEvent -> {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToProfileFragment())
            }
            LoginUIEvent.SignUpEvent -> {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.TMDB_SIGNUP_URL))
                startActivity(browserIntent)
            }
        }
    }
}