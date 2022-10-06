package com.karrar.movieapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentSearchBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.search.adapters.SearchHistoryAdapter
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(false)

        observeEvents()

        binding.recyclerSearchHistory.adapter = SearchHistoryAdapter(mutableListOf(), viewModel)
        lifecycleScope.launch {
            viewModel.mediaType.collect {
                if (it == "movie" || it == "tv") {
                    bindMedia()
                } else {
                    bindActors()
                }
            }
        }
    }

    private fun observeEvents() {
        navigateToMovieDetails()
        navigateToSeriesDetails()
        navigateToActorDetails()
    }

    private fun navigateToMovieDetails() {
        if (viewModel.mediaType.value == "movie") {
            viewModel.clickMediaEvent.observe(viewLifecycleOwner, EventObserve {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(
                        it
                    )
                )
            })
        }
    }

    private fun navigateToSeriesDetails() {
        if (viewModel.mediaType.value == "tv") {
        }
    }

    private fun navigateToActorDetails() {
        viewModel.clickActorEvent.observe(viewLifecycleOwner, EventObserve { actorID ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToActorDetailsFragment(
                    actorID
                )
            )
        })
    }

    private fun bindMedia() {
        binding.recyclerMedia.apply {
            adapter = MediaSearchAdapter(mutableListOf(), viewModel)
            layoutManager =
                LinearLayoutManager(this@SearchFragment.context, RecyclerView.VERTICAL, false)
        }
    }

    private fun bindActors() {
        binding.recyclerMedia.apply {
            adapter = PersonAdapter(mutableListOf(), viewModel)
            layoutManager = GridLayoutManager(this@SearchFragment.context, 3)
        }
    }
}