package com.karrar.movieapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.karrar.movieapp.BR

abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {

    protected abstract val layoutIdActivity: Int

    private lateinit var _binding: VDB
    protected val binding get() = _binding

    protected abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView<VDB>(this, layoutIdActivity).apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = this@BaseActivity
        }
    }

}