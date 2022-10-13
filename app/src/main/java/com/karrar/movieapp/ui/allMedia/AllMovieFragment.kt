package com.karrar.movieapp.ui.allMedia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentAllMovieBinding
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.adapters.MediaLoadStateAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
import com.karrar.movieapp.utilities.collect
import com.karrar.movieapp.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class AllMovieFragment() : BaseFragment<FragmentAllMovieBinding>() {
    override val layoutIdFragment = R.layout.fragment_all_movie
    override val viewModel: AllMovieViewModel by viewModels()
    private val allMediaAdapter: AllMediaAdapter by lazy { AllMediaAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, viewModel.args.type.value)
        setMovieAdapter()
        observeEvents()
    }

    private fun setMovieAdapter() {
        val footerAdapter = MediaLoadStateAdapter(allMediaAdapter::retry)
        binding.recyclerMedia.adapter = allMediaAdapter.withLoadStateFooter(footerAdapter)
        setSnapSize(footerAdapter)

        collect(flow = allMediaAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }.map { it.refresh },
            action = { viewModel.setErrorUiState(it) })

        collectLast(viewModel.allMedia, ::setAllMedia)
    }

    private fun setSnapSize(footerAdapter: MediaLoadStateAdapter) {
        val mManager = binding.recyclerMedia.layoutManager as GridLayoutManager
        mManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if ((position == allMediaAdapter.itemCount)
                    && footerAdapter.itemCount > 0
                ) {
                    mManager.spanCount
                } else {
                    1
                }
            }
        }
    }

    private suspend fun setAllMedia(itemsPagingData: PagingData<Media>) {
        allMediaAdapter.submitData(itemsPagingData)
    }

    private fun observeEvents() {
        viewModel.backEvent.observe(viewLifecycleOwner, EventObserve { removeFragment() })
        viewModel.clickMovieEvent.observe(
            viewLifecycleOwner,
            EventObserve { movieID -> seeMovieDetails(movieID) })
    }

    private fun seeMovieDetails(movieID: Int) {
        findNavController().navigate(
            AllMovieFragmentDirections.actionAllMovieFragmentToMovieDetailFragment(
                movieID
            )
        )
    }

    private fun removeFragment() {
        findNavController().popBackStack()
    }

}