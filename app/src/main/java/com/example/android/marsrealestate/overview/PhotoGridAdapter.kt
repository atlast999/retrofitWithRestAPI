
package com.example.android.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.databinding.GridViewItemBinding
import com.example.android.marsrealestate.network.MarsProperty

class PhotoGridAdapter(val itemClickListener: (MarsProperty) -> Unit) : ListAdapter<MarsProperty, PhotoGridAdapter.ViewHolder>(MarsPropertyDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, getItem(position), itemClickListener)
    }

    class ViewHolder(private val binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(position: Int, property: MarsProperty, itemClickListener: (MarsProperty) -> Unit){
            binding.property = property
            binding.root.setOnClickListener { itemClickListener(property) }
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val binding = GridViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class MarsPropertyDiffCallBack: DiffUtil.ItemCallback<MarsProperty>(){
    override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem == newItem
    }

}

