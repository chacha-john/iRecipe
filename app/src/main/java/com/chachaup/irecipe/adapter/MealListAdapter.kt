package com.chachaup.irecipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chachaup.irecipe.data.Meal
import com.chachaup.irecipe.databinding.MealItemBinding
import com.squareup.picasso.Picasso

class MealListAdapter(private val onClick: (Meal) -> Unit) : ListAdapter<Meal, MealListAdapter.ItemViewHolder>(DiffCallback) {

    class ItemViewHolder(private val binding: MealItemBinding, val onClick: (Meal) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        private var currentMeal: Meal? = null
        fun bind(meal: Meal) {
            binding.name.text = meal.strMeal
            Picasso.get().load(meal.strMealThumb).into(binding.image)
            currentMeal = meal
        }

        init {
            itemView.setOnClickListener{
                currentMeal?.let {
                    onClick(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClick
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Meal>() {
            override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem.idMeal == newItem.idMeal
            }

            override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem == newItem
            }

        }
    }


}