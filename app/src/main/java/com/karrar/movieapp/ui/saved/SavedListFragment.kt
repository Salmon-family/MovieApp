package com.karrar.movieapp.ui.saved

import android.os.Bundle
 import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentSavedListBinding
import com.karrar.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedListFragment : BaseFragment<FragmentSavedListBinding>() {
    override val layoutIdFragment = R.layout.fragment_saved_list
    private  val arguments: SavedListFragmentArgs by navArgs()
    override val viewModel: SavedListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListDetailsById(arguments.id.toString())
        setSavedPublicListsObserver()
    }
    private fun setSavedPublicListsObserver() {
             val adapter = ListDetailsAdapter(mutableListOf(), viewModel)
            binding.lists.adapter = adapter

    }
}