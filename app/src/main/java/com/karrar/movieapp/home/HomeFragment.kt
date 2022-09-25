package com.karrar.movieapp.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseFragment
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.home.adapters.*


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    private val data2 = listOf("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8")
    private val data3 =
        listOf("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "C11", "C12")

    private val movieImageAdapter = MovieImageAdapter(data3)

    private val concatAdapter: ConcatAdapter by lazy {
        val config = ConcatAdapter.Config.Builder().apply {
            setIsolateViewTypes(false)
        }.build()
        ConcatAdapter(
            config,
            HorizontalImageAdapter(movieImageAdapter),
            HorizontalImageAdapter(movieImageAdapter),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerView.adapter = concatAdapter
    }

}