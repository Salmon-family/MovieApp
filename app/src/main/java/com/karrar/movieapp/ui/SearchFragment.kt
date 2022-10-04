package com.karrar.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.main.SharedViewModel


class SearchFragment : Fragment() {
    private val sharedViewModel by lazy { ViewModelProvider(requireActivity()).get(SharedViewModel::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        sharedViewModel.setToolbar(isVisible = true, isTransparent = false, title = "Search")

        return view
    }

}