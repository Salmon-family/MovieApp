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
import com.karrar.movieapp.ui.adapters.LoadUIStateAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.collect
import com.karrar.movieapp.utilities.collectLast
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllMovieFragment: BaseFragment<FragmentAllMovieBinding>() {
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
        val footerAdapter = LoadUIStateAdapter(allMediaAdapter::retry)
        binding.recyclerMedia.adapter = allMediaAdapter.withLoadStateFooter(footerAdapter)
        setSnapSize(footerAdapter)

        collect(flow = allMediaAdapter.loadStateFlow,
            action = { viewModel.setErrorUiState(it.source.refresh) })

        collectLast(viewModel.allMedia, ::setAllMedia)
    }

    private fun setSnapSize(footerAdapter: LoadUIStateAdapter) {
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
        viewModel.clickMovieEvent.observeEvent(viewLifecycleOwner) {movieID ->
            findNavController().navigate(AllMovieFragmentDirections.actionAllMovieFragmentToMovieDetailFragment(movieID))
        }
        viewModel.clickSeriesEvent.observeEvent(viewLifecycleOwner) {seriesID ->
            findNavController().navigate(AllMovieFragmentDirections.actionAllMovieFragmentToTvShowDetailsFragment(seriesID))
        }

        viewModel.backEvent.observeEvent(viewLifecycleOwner) {removeFragment()  }
    }


    private fun removeFragment() {
        findNavController().popBackStack()
    }

}