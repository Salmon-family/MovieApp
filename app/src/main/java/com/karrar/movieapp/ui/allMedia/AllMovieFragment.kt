package com.karrar.movieapp.ui.allMedia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentAllMovieBinding
import com.devfalah.types.AllMediaType
import com.karrar.movieapp.ui.adapters.LoadUIStateAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.models.MediaUiState
import com.karrar.movieapp.utilities.collect
import com.karrar.movieapp.utilities.collectLast
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
        collectEvent()
    }

    private fun setMovieAdapter() {
        val footerAdapter = LoadUIStateAdapter(allMediaAdapter::retry)
        binding.recyclerMedia.adapter = allMediaAdapter.withLoadStateFooter(footerAdapter)

        val mManager = binding.recyclerMedia.layoutManager as GridLayoutManager
        mManager.setSpanSize(footerAdapter, allMediaAdapter, mManager.spanCount)

        collect(flow = allMediaAdapter.loadStateFlow,
            action = {
                viewModel.setErrorUiState(it)
            })

        collectLast(viewModel.uiState.value.allMedia, ::setAllMedia)
    }


    private suspend fun setAllMedia(itemsPagingData: PagingData<MediaUiState>) {
        allMediaAdapter.submitData(itemsPagingData)
    }

    private fun collectEvent() {
        collectLast(viewModel.mediaUIEvent) {
            it?.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: MediaUIEvent) {
        when (event) {
            MediaUIEvent.BackEvent -> {
                removeFragment()
            }
            is MediaUIEvent.ClickMovieEvent -> {
                findNavController().navigate(
                    AllMovieFragmentDirections.actionAllMovieFragmentToMovieDetailFragment(
                        event.movieID
                    )
                )
            }
            is MediaUIEvent.ClickSeriesEvent -> {
                findNavController().navigate(
                    AllMovieFragmentDirections.actionAllMovieFragmentToTvShowDetailsFragment(
                        event.seriesID
                    )
                )
            }
            MediaUIEvent.RetryEvent -> {
                allMediaAdapter.retry()
            }
        }
    }

    private fun removeFragment() {
        findNavController().popBackStack()
    }

    private fun getTitle(type: com.devfalah.types.AllMediaType): String {
        return when (type) {
            com.devfalah.types.AllMediaType.ON_THE_AIR -> resources.getString(R.string.title_on_air)
            com.devfalah.types.AllMediaType.AIRING_TODAY -> resources.getString(R.string.title_airing_today)
            com.devfalah.types.AllMediaType.LATEST -> resources.getString(R.string.latest)
            com.devfalah.types.AllMediaType.POPULAR -> resources.getString(R.string.popular)
            com.devfalah.types.AllMediaType.TOP_RATED -> resources.getString(R.string.title_top_rated_tv_show)
            com.devfalah.types.AllMediaType.TRENDING -> resources.getString(R.string.title_trending)
            com.devfalah.types.AllMediaType.NOW_STREAMING -> resources.getString(R.string.title_streaming)
            com.devfalah.types.AllMediaType.UPCOMING -> resources.getString(R.string.title_upcoming)
            com.devfalah.types.AllMediaType.MYSTERY -> resources.getString(R.string.title_mystery)
            com.devfalah.types.AllMediaType.ADVENTURE -> resources.getString(R.string.title_adventure)
            com.devfalah.types.AllMediaType.ACTOR_MOVIES -> ""
        }
    }

}