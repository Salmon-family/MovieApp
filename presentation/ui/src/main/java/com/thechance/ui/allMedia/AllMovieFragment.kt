package com.thechance.ui.allMedia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.thechance.ui.R
import com.thechance.ui.adapters.LoadUIStateAdapter
import com.thechance.ui.base.BaseFragment
import com.thechance.ui.databinding.FragmentAllMovieBinding
import com.thechance.ui.utilities.setSpanSize
import com.thechance.viewmodel.allMedia.AllMovieViewModel
import com.thechance.viewmodel.allMedia.MediaUIEvent
import com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.AllMediaType
import com.thechance.viewmodel.models.MediaUiState
import com.thechance.viewmodel.utilities.collect
import com.thechance.viewmodel.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllMovieFragment : BaseFragment<FragmentAllMovieBinding>() {
    override val layoutIdFragment = R.layout.fragment_all_movie
    override val viewModel: AllMovieViewModel by viewModels()
    private val allMediaAdapter: AllMediaAdapter by lazy { AllMediaAdapter(viewModel) }
    private val args: AllMovieFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setMedia(args.id, args.type)
        setTitle(true, getTitle(args.type))
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
            AllMediaType.ACTOR_MOVIES -> ""
        }
    }

}