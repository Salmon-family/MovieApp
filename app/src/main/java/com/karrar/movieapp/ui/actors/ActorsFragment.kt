package com.karrar.movieapp.ui.actors

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentActorsBinding
import com.karrar.movieapp.ui.actors.adapters.ActorsAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorsFragment : BaseFragment<FragmentActorsBinding>() {
    override val layoutIdFragment = R.layout.fragment_actors
    override val viewModel: ActorsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actorsAdapter =
            ActorsAdapter(mutableListOf(), viewModel)
        binding.recyclerViewActors.adapter = actorsAdapter
    }
}