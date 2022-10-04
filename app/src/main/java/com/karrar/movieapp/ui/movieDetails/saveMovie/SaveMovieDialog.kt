package com.karrar.movieapp.ui.movieDetails.saveMovie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.response.ListDto
import com.karrar.movieapp.databinding.DialogSaveMovieBinding
import com.karrar.movieapp.ui.base.BaseDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SaveMovieDialog : BaseDialogFragment<DialogSaveMovieBinding>() {

    override val layoutIdFragment = R.layout.dialog_save_movie
    override val viewModel: SaveMovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveListAdapter.adapter = SaveListAdapter(mutableListOf(), viewModel)

    }

}