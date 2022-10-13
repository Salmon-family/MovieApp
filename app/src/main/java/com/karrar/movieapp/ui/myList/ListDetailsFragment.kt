package com.karrar.movieapp.ui.myList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentListDetailsBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListDetailsFragment() : BaseFragment<FragmentListDetailsBinding>() {
    override val layoutIdFragment = R.layout.fragment_list_details
    override val viewModel: ListDetailsViewModel by viewModels()
    lateinit var listDetailsAdapter: ListDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.itemName.observe(viewLifecycleOwner,EventObserve{
            setTitle(true,it)
        })
        listDetailsAdapter = ListDetailsAdapter(mutableListOf(), viewModel)
        binding.lists.adapter = listDetailsAdapter
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.mediaType.observe(viewLifecycleOwner, EventObserve {
            if (it == "movie") {
                viewModel.itemId.observe(viewLifecycleOwner, EventObserve { id->
                    navigateToMovieDetails(id)
                })
            } else {
                viewModel.itemId.observe(viewLifecycleOwner, EventObserve { id->
                           navigateToTvShowDetails(id)
                })
            }

        })
    }

    private fun navigateToMovieDetails(id: Int) {
        findNavController().navigate(
            ListDetailsFragmentDirections.actionSavedListFragmentToMovieDetailFragment(id)
        )
    }

    private fun navigateToTvShowDetails(id : Int) {
        TODO("Not yet implemented")
    }

}