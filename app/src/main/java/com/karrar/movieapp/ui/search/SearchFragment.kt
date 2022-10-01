package com.karrar.movieapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.databinding.InverseMethod
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentSearchBinding
import com.karrar.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(){
    override val layoutIdFragment: Int = R.layout.fragment_search
    override val viewModel: SearchViewmodel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerMedia.adapter = MediaAdapter(mutableListOf(), viewModel)

        binding.inputSearch.doOnTextChanged { text, start, before, count ->
            if(!text.isNullOrEmpty()){
                viewModel.getMedia(text.toString())
            }
        }
    }
}