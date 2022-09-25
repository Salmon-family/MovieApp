package com.karrar.movieapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.databinding.HorizontalRecyclerBinding

class HorizontalWrapperAdapter2<T>(
    private val adapter: T
) : RecyclerView.Adapter<HorizontalWrapperAdapter2.HorizontalWrapperViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalWrapperViewHolder<T> {
        return HorizontalWrapperViewHolder<T>(
            HorizontalRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HorizontalWrapperViewHolder<T>, position: Int) {
        holder.bind(adapter as RecyclerView.Adapter<*>)
    }

    override fun getItemCount(): Int = 1

    class HorizontalWrapperViewHolder<T>(
        private val binding: HorizontalRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(adapter: Any) {
            binding.recyclerView.adapter = adapter as RecyclerView.Adapter<*>
        }
    }
}


