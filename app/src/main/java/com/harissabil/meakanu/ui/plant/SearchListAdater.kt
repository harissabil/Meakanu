package com.harissabil.meakanu.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.harissabil.meakanu.R
import com.harissabil.meakanu.data.remote.response.trefle.DataItem
import com.harissabil.meakanu.databinding.ItemSearchPlantsBinding
import com.harissabil.meakanu.module.GlideApp

class SearchListAdater(private val plantList: List<DataItem>) :
    RecyclerView.Adapter<SearchListAdater.ViewHolder>() {
    class ViewHolder(var binding: ItemSearchPlantsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSearchPlantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = plantList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = plantList[position]
        holder.binding.apply {
            tvPlantCommonNameSearch.text = list.commonName
            GlideApp.with(holder.itemView.context)
                .load(list.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground_splash)
                .into(ivPlantImage)
            tvPlantScientificName.text = list.scientificName
            tvPlantFamily.text = list.family

            cvPlant.setOnClickListener {
                val id = list.id
                val bundle = Bundle()
                bundle.putInt("id", id)

                cvPlant.findNavController().navigate(
                    R.id.action_navigation_plant_to_plantDetailFragment, bundle
                )
            }
        }
    }
}