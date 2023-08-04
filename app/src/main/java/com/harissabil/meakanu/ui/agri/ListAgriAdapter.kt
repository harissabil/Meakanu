package com.harissabil.meakanu.ui.agri

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.harissabil.meakanu.databinding.ItemAgriInfoBinding

class ListAgriAdapter(private val listAgriInfo: ArrayList<AgriInfo>) :
    RecyclerView.Adapter<ListAgriAdapter.ListViewHolder>() {
    class ListViewHolder(var binding: ItemAgriInfoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemAgriInfoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listAgriInfo.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val list = listAgriInfo[position]
        holder.binding.tvTitle.text = list.title
        holder.binding.tvSource.text = list.source

        val link = list.link
        holder.binding.cvAgriInfo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(holder.itemView.context, intent, null)
        }
    }
}