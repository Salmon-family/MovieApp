package com.karrar.movieapp.ui.search

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.transition.ChangeTransform
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentSearchBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.search.adapters.PersonAdapter
import com.karrar.movieapp.ui.search.adapters.SearchHistoryAdapter
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition = ChangeTransform()
        setTitle(false)

        val inputMethodManager = binding.inputSearch.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.inputSearch,InputMethodManager.SHOW_IMPLICIT)

        observeEvents()

        binding.recyclerSearchHistory.adapter = SearchHistoryAdapter(mutableListOf(), viewModel)
        lifecycleScope.launch {
            viewModel.mediaType.collect {
                if (it == Constants.MOVIE || it == Constants.TV_SHOWS) {
                    bindMedia()
                } else {
                    bindActors()
                }
            }
        }
    }

    private fun observeEvents() {
        if (viewModel.mediaType.value == Constants.MOVIE) {
            viewModel.clickMediaEvent.observe(viewLifecycleOwner, EventObserve {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(
                        it
                    )
                )
            })
        }

        if (viewModel.mediaType.value == Constants.TV_SHOWS) {

        }

        viewModel.clickActorEvent.observe(viewLifecycleOwner, EventObserve { actorID ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToActorDetailsFragment(
                    actorID
                )
            )
        })

        viewModel.clickBackEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().popBackStack()
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