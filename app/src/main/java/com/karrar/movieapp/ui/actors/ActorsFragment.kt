package com.karrar.movieapp.ui.actors

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentActorsBinding
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.adapters.LoadUIStateAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.collect
import com.karrar.movieapp.utilities.collectLast
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorsFragment : BaseFragment<FragmentActorsBinding>() {
    override val layoutIdFragment = R.layout.fragment_actors
    override val viewModel: ActorsViewModel by viewModels()
    private val actorsAdapter by lazy { ActorsAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, resources.getString(R.string.actors))
        setAdapter()
        observeEvents()
    }

    private fun setAdapter() {
        val footerAdapter = LoadUIStateAdapter(actorsAdapter::retry)
        binding.recyclerViewActors.adapter = actorsAdapter.withLoadStateFooter(footerAdapter)

        setSnapSize(footerAdapter)

        collect(flow = actorsAdapter.loadStateFlow,
            action = { viewModel.setErrorUiState(it.source.refresh) })

        collectLast(viewModel.trendingActors, ::setAllActors)
    }

    private fun setSnapSize(footerAdapter: LoadUIStateAdapter) {
        val mManager = binding.recyclerViewActors.layoutManager as GridLayoutManager
        mManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if ((position == actorsAdapter.itemCount)
                    && footerAdapter.itemCount > 0
                ) {
                    mManager.spanCount
                } else {
                    1
                }
            }
        }
    }

    private suspend fun setAllActors(itemsPagingData: PagingData<Actor>) {
        actorsAdapter.submitData(itemsPagingData)
    }

    private fun observeEvents() {
        viewModel.clickActorEvent.observeEvent(viewLifecycleOwner){ actorID ->
            findNavController()
                .navigate(ActorsFragmentDirections.actionActorsFragmentToActorDetailsFragment(actorID))
        }

        viewModel.clickRetryEvent.observeEvent(viewLifecycleOwner) {
            if (it) { actorsAdapter.retry() }
        }
    }
}