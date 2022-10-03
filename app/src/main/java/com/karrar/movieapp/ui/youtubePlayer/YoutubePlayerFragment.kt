package com.karrar.movieapp.ui.youtubePlayer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentYoutubePlayerBinding
import com.karrar.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class YoutubePlayerFragment : BaseFragment<FragmentYoutubePlayerBinding>() {

    override val layoutIdFragment = R.layout.fragment_youtube_player
    override val viewModel: YoutubePlayerViewModel by viewModels()

    private val args: YoutubePlayerFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieTrailer(args.movieId)
    }

}