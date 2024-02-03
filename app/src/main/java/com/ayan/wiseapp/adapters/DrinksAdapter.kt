package com.ayan.wiseapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayan.wiseapp.databinding.ItemDrinkBinding
import com.ayan.wiseapp.interfaces.ListItemClickListener
import com.ayan.wiseapp.models.Drink
import com.bumptech.glide.Glide

class DrinksAdapter(
    private val itemList: List<Drink>,
    private val listener: ListItemClickListener
) : RecyclerView.Adapter<DrinksAdapter.DrinkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDrinkBinding.inflate(inflater, parent, false)
        return DrinkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener.onItemClicked(item) }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class DrinkViewHolder(private val binding: ItemDrinkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Drink) {
            binding.apply {
                tvName.text = item.strDrink
                Glide.with(binding.root.context)
                    .load(item.strDrinkThumb)
                    .into(ivDrink)
            }
        }
    }
}