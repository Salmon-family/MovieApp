package com.thechance.movie.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.thechance.movie.BR

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {
    abstract val layoutIdFragment: Int
    abstract val viewModel: ViewModel

    private lateinit var _binding: VDB
    protected val binding: VDB
        get() = _binding

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

    protected fun setTitle(visibility: Boolean, title: String? = null) {
        if (visibility) {
            (activity as AppCompatActivity).supportActionBar?.show()
            title?.let {
                (activity as AppCompatActivity).supportActionBar?.title = it
            }
        } else {
            (activity as AppCompatActivity).supportActionBar?.hide()
        }
    }

}