package com.custard.openinapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.custard.openinapp.databinding.CardLinkBinding
import com.custard.openinapp.network.data.LinkData
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class LinkAdapter(val onTextClickListener: (String) -> Unit): Adapter<LinkAdapter.ViewHolder>() {
    var data = emptyList<LinkData>()

    class ViewHolder(val binding: CardLinkBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardLinkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            Picasso.get().load(data[position].originalImage).into(image)
            title.text = data[position].title
            date.text = data[position].createdAt.toSimpleFormat()
            link.text = data[position].webLink
            clicks.text = data[position].totalClicks.toString()
            linkView.setOnClickListener {
                onTextClickListener(data[position].webLink)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}

fun String.toSimpleFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    outputFormat.timeZone = TimeZone.getDefault()

    val date: Date? = inputFormat.parse(this)
    return if (date != null) {
        outputFormat.format(date)
    } else {
        "Invalid timestamp"
    }
}