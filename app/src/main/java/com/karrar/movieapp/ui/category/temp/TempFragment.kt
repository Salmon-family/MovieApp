package com.karrar.movieapp.ui.category.temp

import androidx.fragment.app.viewModels
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentTempBinding
import com.karrar.movieapp.ui.base.BaseFragment

class TempFragment : BaseFragment<FragmentTempBinding>() {

    override val layoutIdFragment = R.layout.fragment_temp
    override val viewModel: TempViewModel by viewModels()

}