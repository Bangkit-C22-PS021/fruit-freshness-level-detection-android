package com.example.capstoneproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.capstoneproject.databinding.GridItemBinding

class FruitAdapter : RecyclerView.Adapter<FruitAdapter.UserViewHolder>() {
    private val list = ArrayList<FruitDataItem>()

    fun setList(users: List<FruitDataItem>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(fruitDataItem: FruitDataItem){
            binding.apply {
                Glide.with(itemView)
                    .load(fruitDataItem.img)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(gridImage)
                gridName.text = fruitDataItem.fruitName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = GridItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}