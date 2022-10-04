package com.karrar.movieapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentSearchBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.search.adapters.SearchHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(){
    override val layoutIdFragment: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerSearchHistory.adapter = SearchHistoryAdapter(mutableListOf(), viewModel)
        lifecycleScope.launch {
            viewModel.mediaType.collect{
                if(it == "movie" || it == "tv"){
                    binding.recyclerMedia.apply {
                        adapter = MediaAdapter(mutableListOf(), viewModel)
                        layoutManager = LinearLayoutManager(this@SearchFragment.context, RecyclerView.VERTICAL, false)
                    }
                }else{
                    binding.recyclerMedia.apply {
                        adapter = PersonAdapter(mutableListOf(), viewModel)
                        layoutManager = GridLayoutManager(this@SearchFragment.context, 3)
                    }
                }
            }
        }
    }
}