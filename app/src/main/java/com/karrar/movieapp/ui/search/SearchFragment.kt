package com.karrar.movieapp.ui.search

import android.content.Context
import android.os.Bundle
import android.transition.ChangeTransform
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentSearchBinding
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.LoadUIStateAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.search.adapters.MediaSearchAdapter
import com.karrar.movieapp.ui.search.adapters.ActorSearchAdapter
import com.karrar.movieapp.ui.search.adapters.SearchHistoryAdapter
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.collect
import com.karrar.movieapp.utilities.collectLast
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()
    private val mediaSearchAdapter by lazy { MediaSearchAdapter(viewModel) }
    private val actorSearchAdapter by lazy { ActorSearchAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition = ChangeTransform()
        setTitle(false)

        val inputMethodManager =
            binding.inputSearch.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.inputSearch, InputMethodManager.SHOW_IMPLICIT)

        observeEvents()
        getSearchResults()

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

        viewModel.clickMediaEvent.observeEvent(viewLifecycleOwner) {
            when (it.mediaType) {
                Constants.MOVIE -> navigateToMovieDetails(it.mediaID)

                Constants.TV_SHOWS -> navigateToSeriesDetails(it.mediaID)
            }
        }

        viewModel.clickActorEvent.observeEvent(viewLifecycleOwner) { actorID ->
            navigateToActorDetails(actorID)
        }

        viewModel.clickBackEvent.observeEvent(viewLifecycleOwner) {
            popFragment()
        }

        viewModel.clickRetryEvent.observeEvent(viewLifecycleOwner) {
            if (it && viewModel.mediaType.value == Constants.ACTOR){
                actorSearchAdapter.retry()
            }else{
                mediaSearchAdapter.retry()
            }
        }

        viewModel.clickMoviesCategoryEvent.observeEvent(viewLifecycleOwner){ isClicked ->
            if(isClicked){
                getMovieSearchResults(viewModel.searchText.value)
            }else{
                getSearchResults()
            }
        }

        viewModel.clickSeriesCategoryEvent.observeEvent(viewLifecycleOwner){ isClicked ->
            if(isClicked){
                getSeriesSearchResults(viewModel.searchText.value)
            }else{
                getSearchResults()
            }
        }

        viewModel.clickActorsCategoryEvent.observeEvent(viewLifecycleOwner){ isClicked ->
            if(isClicked){
                getActorsSearchResults(viewModel.searchText.value)
            }else{
                getSearchResults()
            }
        }
    }

    private fun navigateToMovieDetails(movieId: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(
                movieId
            )
        )
    }

    private fun navigateToSeriesDetails(seriesId: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToTvShowDetailsFragment(
                seriesId
            )
        )
    }


    private fun navigateToActorDetails(actorId: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToActorDetailsFragment(
                actorId
            )
        )
    }

    private fun bindMedia() {
        val footerAdapter = LoadUIStateAdapter(mediaSearchAdapter::retry)
        binding.recyclerMedia.adapter = mediaSearchAdapter.withLoadStateFooter(footerAdapter)
        binding.recyclerMedia.layoutManager = LinearLayoutManager(this@SearchFragment.context, RecyclerView.VERTICAL, false)

        collect(flow = mediaSearchAdapter.loadStateFlow,
                action = {viewModel.setUiState(it.source.refresh, mediaSearchAdapter.itemCount)})
    }

    private fun bindActors() {
        val footerAdapter = LoadUIStateAdapter(actorSearchAdapter::retry)
        binding.recyclerMedia.adapter = actorSearchAdapter.withLoadStateFooter(footerAdapter)
        binding.recyclerMedia.layoutManager = GridLayoutManager(this@SearchFragment.context, 3)

        setSpanSize(footerAdapter)

        collect(flow = actorSearchAdapter.loadStateFlow,
                action = {viewModel.setUiState(it.source.refresh, actorSearchAdapter.itemCount)})
    }

    @OptIn(FlowPreview::class)
    private fun getSearchResults(){
        lifecycleScope.launch {
            viewModel.searchText.debounce(500).collect{ search ->
                if (search.isNotBlank()) {
                    when (viewModel.mediaType.value) {
                        Constants.MOVIE -> {
                            getMovieSearchResults(search)
                        }
                        Constants.TV_SHOWS -> {
                            getSeriesSearchResults(search)
                        }
                        Constants.ACTOR -> {
                            getActorsSearchResults(search)
                        }
                    }
                }
            }
        }
    }

    private fun getMovieSearchResults(searchText: String){
        mediaSearchAdapter.submitData(lifecycle, PagingData.empty())
        collectLast(viewModel.searchForMovie(searchText))
        {mediaSearchAdapter.submitData(it)}
    }

    private fun getSeriesSearchResults(searchText: String){
        mediaSearchAdapter.submitData(lifecycle, PagingData.empty())
        collectLast(viewModel.searchForSeries(searchText))
        {mediaSearchAdapter.submitData(it)}
    }

    private fun getActorsSearchResults(searchText: String){
        actorSearchAdapter.submitData(lifecycle, PagingData.empty())
        collectLast(viewModel.searchForActor(searchText))
        {actorSearchAdapter.submitData(it)}
    }

    private fun setSpanSize(footerAdapter: LoadUIStateAdapter) {
        val mManager = binding.recyclerMedia.layoutManager as GridLayoutManager
        mManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if ((position == actorSearchAdapter.itemCount)
                    && footerAdapter.itemCount > 0
                ) {
                    mManager.spanCount
                } else {
                    1
                }
            }
        }
    }

    private fun popFragment() {
        findNavController().popBackStack()
    }
}