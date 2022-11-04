package com.thechance.movie.ui.base

import android.os.Bundle
import android.view.*
import androidx.databinding.*
import androidx.lifecycle.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.thechance.movie.BR


abstract class BaseDialogFragment<VDB : ViewDataBinding> : BottomSheetDialogFragment() {

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
            lifecycleOwner = this@BaseDialogFragment
            setVariable(BR.viewModel, viewModel)
            return root
        }
    }
}