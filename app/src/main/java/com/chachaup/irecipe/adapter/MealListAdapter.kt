package com.chachaup.irecipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chachaup.irecipe.data.MealItem
import com.chachaup.irecipe.databinding.MealItemBinding
import com.squareup.picasso.Picasso

class MealListAdapter : ListAdapter<MealItem, MealListAdapter.ItemViewHolder>(DiffCallback) {

    class ItemViewHolder(private val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mealItem: MealItem) {
            binding.name.text = mealItem.name
            Picasso.get().load(mealItem.pictureUrl).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            getItem(position)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<MealItem>() {
            override fun areItemsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
                return oldItem == newItem
            }

        }
    }


}