package com.karrar.movieapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.databinding.HorizontalRecyclerBinding
import com.karrar.movieapp.home.HomeViewModel

abstract class HorizontalBaseAdapter<T>(
    private val adapter: BaseAdapter<T>,
    private val viewModel: BaseViewModel
) : RecyclerView.Adapter<HorizontalBaseAdapter.HorizontalWrapperViewHolder<T>>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalWrapperViewHolder<T> {
        return HorizontalWrapperViewHolder(
            HorizontalRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HorizontalWrapperViewHolder<T>, position: Int) {
        holder.bind(adapter, viewModel)
    }

    override fun getItemCount(): Int = 1

    class HorizontalWrapperViewHolder<T>(
        private val binding: HorizontalRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(adapter: BaseAdapter<T>, viewModel: BaseViewModel) {
            binding.recyclerView.adapter = adapter
            binding.viewModel = viewModel as HomeViewModel?
        }
    }
}


