package com.karrar.movieapp.ui.actors

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentActorsBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ActorsFragment : BaseFragment<FragmentActorsBinding>() {
    override val layoutIdFragment = R.layout.fragment_actors
    override val viewModel: ActorsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, resources.getString(R.string.actors))
        setAdapter()
        observeEvents()
    }

    private fun setAdapter(){
        val actorsAdapter = ActorsAdapter(viewModel)
        binding.recyclerViewActors.adapter = actorsAdapter
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.trendingActors.collectLatest { pagingData ->
                actorsAdapter.submitData(pagingData)
            }
        }
    }

    private fun observeEvents() {
        viewModel.clickActorEvent.observe(viewLifecycleOwner, EventObserve { actorID ->
            findNavController()
                .navigate(
                    ActorsFragmentDirections.actionActorsFragmentToActorDetailsFragment(
                        actorID
                    )
                )
        })
    }
}