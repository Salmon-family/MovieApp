package com.karrar.movieapp.ui.youtubePlayer

import android.os.Bundle
import androidx.activity.viewModels
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.ActivityYoutubePlayerBinding
import com.karrar.movieapp.ui.base.BaseActivity
import com.karrar.movieapp.utilities.fullScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YoutubePlayerActivity : BaseActivity<ActivityYoutubePlayerBinding>() {

    override val layoutIdActivity: Int = R.layout.activity_youtube_player
    override val viewModel: YoutubePlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.fullScreen(window)
        supportActionBar?.hide()
    }
}