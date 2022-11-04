package com.thechance.movie.ui.actors

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.thechance.movie.ui.adapters.LoadUIStateAdapter
import com.thechance.movie.ui.base.BaseFragment
import com.thechance.movie.ui.models.ActorUiState
import com.thechance.movie.R
import com.thechance.movie.databinding.FragmentActorsBinding
import com.thechance.movie.utilities.collect
import com.thechance.movie.utilities.collectLast
import com.thechance.movie.utilities.setSpanSize
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
        collectEvent()
    }

    private fun setAdapter() {
        val footerAdapter = LoadUIStateAdapter(actorsAdapter::retry)
        binding.recyclerViewActors.adapter = actorsAdapter.withLoadStateFooter(footerAdapter)

        val mManager = binding.recyclerViewActors.layoutManager as GridLayoutManager
        mManager.setSpanSize(footerAdapter, actorsAdapter, mManager.spanCount)

        collect(flow = actorsAdapter.loadStateFlow,
            action = { viewModel.setErrorUiState(it) })

        collectLast(viewModel.uiState.value.actors, ::setAllActors)
    }

    private suspend fun setAllActors(itemsPagingData: PagingData<ActorUiState>) {
        actorsAdapter.submitData(itemsPagingData)
    }

    private fun collectEvent() {
        collectLast(viewModel.actorsUIEventFlow) {
            it?.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: ActorsUIEvent) {
        when (event) {
            is ActorsUIEvent.ActorEvent -> {
                findNavController().navigate(
                    ActorsFragmentDirections.actionActorsFragmentToActorDetailsFragment(
                        event.actorID
                    )
                )
            }
            ActorsUIEvent.RetryEvent -> {
                actorsAdapter.retry()
            }
        }
    }

}