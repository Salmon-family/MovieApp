package com.karrar.movieapp.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.databinding.HorizontalRecyclerBinding

abstract class HorizontalBaseAdapter<T>(private val adapter: BaseAdapter<T>)

    : RecyclerView.Adapter<HorizontalBaseAdapter.HorizontalWrapperViewHolder<T>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalWrapperViewHolder<T> {
        return HorizontalWrapperViewHolder(
            HorizontalRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HorizontalWrapperViewHolder<T>, position: Int) {
        holder.bind(adapter)
    }

    override fun getItemCount(): Int = 1

    class HorizontalWrapperViewHolder<T>(
        private val binding: HorizontalRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(adapter: BaseAdapter<T>) {
            binding.recyclerView.adapter = adapter
        }
    }
}


