package com.marcosanjos.app_agenda.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcosanjos.app_agenda.R
import com.marcosanjos.app_agenda.model.Item

class ItemAdapter(
    private val items: List<Item>
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.image)
        val fullNameTextView = view.findViewById<TextView>(R.id.name)
        val ageTextView = view.findViewById<TextView>(R.id.age)
        val professionTextView = view.findViewById<TextView>(R.id.profession)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemAdapter.ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val item = items[position]
        holder.fullNameTextView.text = item.value.fullName
        holder.ageTextView.text = holder.itemView.context.getString(R.string.item_age, item.value.age)
        holder.professionTextView.text = item.value.profession
    }

    override fun getItemCount(): Int = items.size
}
