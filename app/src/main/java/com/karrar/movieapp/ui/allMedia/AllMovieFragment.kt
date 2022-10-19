package com.karrar.movieapp.ui.allMedia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentAllMovieBinding
import com.karrar.movieapp.domain.enums.AllMediaType
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.adapters.LoadUIStateAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.collect
import com.karrar.movieapp.utilities.collectLast
import com.karrar.movieapp.utilities.observeEvent
import com.karrar.movieapp.utilities.setSpanSize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllMovieFragment : BaseFragment<FragmentAllMovieBinding>() {
    override val layoutIdFragment = R.layout.fragment_all_movie
    override val viewModel: AllMovieViewModel by viewModels()
    private val allMediaAdapter: AllMediaAdapter by lazy { AllMediaAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getTitle(viewModel.args.type))
        setMovieAdapter()
        observeEvents()
    }

    private fun setMovieAdapter() {
        val footerAdapter = LoadUIStateAdapter(allMediaAdapter::retry)
        binding.recyclerMedia.adapter = allMediaAdapter.withLoadStateFooter(footerAdapter)

        val mManager = binding.recyclerMedia.layoutManager as GridLayoutManager
        mManager.setSpanSize(footerAdapter, allMediaAdapter, mManager.spanCount)

        collect(flow = allMediaAdapter.loadStateFlow,
            action = { viewModel.setErrorUiState(it.source.refresh) })

        collectLast(viewModel.allMedia, ::setAllMedia)
    }


    private suspend fun setAllMedia(itemsPagingData: PagingData<Media>) {
        allMediaAdapter.submitData(itemsPagingData)
    }

    private fun observeEvents() {
        viewModel.clickMovieEvent.observeEvent(viewLifecycleOwner) { movieID ->
            findNavController().navigate(
                AllMovieFragmentDirections.actionAllMovieFragmentToMovieDetailFragment(
                    movieID
                )
            )
        }
        viewModel.clickSeriesEvent.observeEvent(viewLifecycleOwner) { seriesID ->
            findNavController().navigate(
                AllMovieFragmentDirections.actionAllMovieFragmentToTvShowDetailsFragment(
                    seriesID
                )
            )
        }
        viewModel.backEvent.observeEvent(viewLifecycleOwner) { removeFragment() }

        viewModel.clickRetryEvent.observeEvent(viewLifecycleOwner) {
            if (it) {
                allMediaAdapter.retry()
            }
        }
    }

    private fun removeFragment() {
        findNavController().popBackStack()
    }

    private fun getTitle(type: AllMediaType): String {
        return when (type) {
            AllMediaType.ON_THE_AIR -> resources.getString(R.string.title_on_air)
            AllMediaType.AIRING_TODAY -> resources.getString(R.string.title_airing_today)
            AllMediaType.LATEST -> resources.getString(R.string.latest)
            AllMediaType.POPULAR -> resources.getString(R.string.popular)
            AllMediaType.TOP_RATED -> resources.getString(R.string.title_top_rated_tv_show)
            AllMediaType.TRENDING -> resources.getString(R.string.title_trending)
            AllMediaType.NOW_STREAMING -> resources.getString(R.string.title_streaming)
            AllMediaType.UPCOMING -> resources.getString(R.string.title_upcoming)
            AllMediaType.MYSTERY -> resources.getString(R.string.title_mystery)
            AllMediaType.ADVENTURE -> resources.getString(R.string.title_adventure)
            AllMediaType.NON -> ""
        }
    }

}