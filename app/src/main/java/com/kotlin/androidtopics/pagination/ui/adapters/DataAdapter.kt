package com.kotlin.androidtopics.pagination.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.androidtopics.databinding.DataTemBinding
import com.kotlin.androidtopics.pagination.names

class DataAdapter(
    private val list: List<names>
) : RecyclerView.Adapter<DataAdapter.DataViewHolder>(){

    lateinit var binding: DataTemBinding

    inner class DataViewHolder(itemview: DataTemBinding) : RecyclerView.ViewHolder(itemview.root) {
        fun bind(names: names) {
            binding.tvData.text = names.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
    
        binding = DataTemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int {
    
        return list.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(list[position])
    }


}