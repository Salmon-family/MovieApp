package com.karrar.movieapp.ui.actorDetails

import androidx.fragment.app.viewModels
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentAllMovieOfActorBinding
import com.karrar.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllMovieOfActorFragment : BaseFragment<FragmentAllMovieOfActorBinding>() {
    override val layoutIdFragment = R.layout.fragment_all_movie_of_actor
    override val viewModel: ActorViewModel by viewModels()


}