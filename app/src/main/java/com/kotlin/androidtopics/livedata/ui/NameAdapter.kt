package com.kotlin.androidtopics.livedata.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.androidtopics.databinding.SingleItemBinding
import com.kotlin.androidtopics.livedata.data.models.Names

class NameAdapter(
    private var list: List<Names>
) : RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    inner class NameViewHolder(itemView: SingleItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(names: Names) {
            binding.tvName.text = names.name
        }
    }

    lateinit var binding: SingleItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NameViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateList(list :List<Names>){
        this.list = list
        notifyDataSetChanged()
    }

}