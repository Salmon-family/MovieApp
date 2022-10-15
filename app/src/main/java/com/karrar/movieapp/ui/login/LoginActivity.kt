package com.karrar.movieapp.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.ActivityLoginBinding
import com.karrar.movieapp.ui.base.BaseActivity
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override val layoutIdActivity = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        observeEvents()
    }


    private fun observeEvents() {
        viewModel.loginEvent.observeEvent(this) {
            finish()
        }

        viewModel.signUpEvent.observeEvent(this) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.TMDB_SIGNUP_URL))
            startActivity(browserIntent)
        }
    }

}