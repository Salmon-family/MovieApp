package com.karrar.movieapp.ui.myList.listDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentListDetailsBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListDetailsFragment : BaseFragment<FragmentListDetailsBinding>() {
    override val layoutIdFragment = R.layout.fragment_list_details
    override val viewModel: ListDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, viewModel.args.listName)
        binding.lists.adapter = ListDetailsAdapter(mutableListOf(), viewModel)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.onItemSelected.observeEvent(viewLifecycleOwner) {
            if (it.mediaType == Constants.MOVIE) {
                navigateToMovieDetails(it.mediaID)
            } else {
                navigateToTvShowDetails(it.mediaID)
            }
        }
    }

    private fun navigateToMovieDetails(id: Int) {
        findNavController().navigate(
            ListDetailsFragmentDirections.actionSavedListFragmentToMovieDetailFragment(id)
        )
    }

    private fun navigateToTvShowDetails(id: Int) {
        findNavController().navigate(
            ListDetailsFragmentDirections.actionListDetailsFragmentToTvShowDetailsFragment(id)
        )
    }

}