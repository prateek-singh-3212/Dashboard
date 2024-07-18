package com.custard.openinapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.custard.openinapp.databinding.CardDataBinding
import com.custard.openinapp.network.data.TopData

class TopDataAdapter(): Adapter<TopDataAdapter.ViewHolder>() {
    var data = emptyList<TopData>()

    class ViewHolder(val binding: CardDataBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            icon.setImageDrawable(data[position].image)
            value.text = data[position].value
            text.text = data[position].title
        }
    }

    override fun getItemCount(): Int = data.size
}