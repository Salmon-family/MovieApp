package com.karrar.movieapp.ui.explore

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentExploringBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploringFragment : BaseFragment<FragmentExploringBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_exploring
    override val viewModel: ExploringViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    private fun observeEvents(){
        navigateToSearch()
    }

    private fun navigateToSearch(){
        val extras = FragmentNavigatorExtras(
            binding.inputSearch to "search_box"
        )
//        viewModel.clickSearchEvent.observe(viewLifecycleOwner, EventObserve{
//            Navigation.findNavController(binding.root).navigate(action, extras)
//        })
        lifecycleScope.launch {
            viewModel.searchText.debounce(1000).collect{
                if(!it.isNullOrEmpty()){
                    Navigation.findNavController(binding.root)
                        .navigate(ExploringFragmentDirections.actionExploringFragmentToSearchFragment(it), extras)
                }
            }
        }
    }
}