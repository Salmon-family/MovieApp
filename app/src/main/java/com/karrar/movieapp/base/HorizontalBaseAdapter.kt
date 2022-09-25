package com.karrar.movieapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.BR

abstract class HorizontalBaseAdapter<T>(
    private var adapter: T
) : RecyclerView.Adapter<HorizontalBaseAdapter.HorizontalBaseViewHolder>() {

    abstract val layoutID: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalBaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), layoutID, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HorizontalBaseViewHolder, position: Int) {
        if (holder is ItemViewHolder) bind(holder)
    }

    override fun getItemCount() = 1

    private fun bind(holder: ItemViewHolder) {
        holder.binding.apply {
            setVariable(BR.adapter, adapter)
        }
    }

    class ItemViewHolder(val binding: ViewDataBinding) : HorizontalBaseViewHolder(binding)

    abstract class HorizontalBaseViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)

}