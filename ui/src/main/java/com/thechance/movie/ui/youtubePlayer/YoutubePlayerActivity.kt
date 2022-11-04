package com.thechance.movie.ui.youtubePlayer

import android.os.Bundle
import android.view.Window
import androidx.activity.viewModels
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.thechance.movie.R
import com.thechance.movie.databinding.ActivityYoutubePlayerBinding
import com.thechance.movie.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YoutubePlayerActivity : BaseActivity<ActivityYoutubePlayerBinding>() {

    override val layoutIdActivity: Int = R.layout.activity_youtube_player
    override val viewModel: YoutubePlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen(window)
        supportActionBar?.hide()
    }

    private fun fullScreen(window: Window) {
        WindowInsetsControllerCompat(window, binding.youtubePlayer).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}