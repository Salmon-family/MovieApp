package com.karrar.movieapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karrar.movieapp.BR
import com.karrar.movieapp.ui.main.SharedViewModel

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {
    abstract val layoutIdFragment: Int
    abstract val viewModel: ViewModel

    private lateinit var _binding: VDB
    protected val binding: VDB
        get() = _binding

    val sharedViewModel by lazy { ViewModelProvider(requireActivity()).get(SharedViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutIdFragment, container, false)
        _binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
            return root
        }
    }


}