package com.karrar.movieapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.home.HomeViewModel


class SearchFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutIdFragment = R.layout.fragment_search
    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(true,"Search")
    }

}