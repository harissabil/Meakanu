package com.harissabil.meakanu.ui.home

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.harissabil.meakanu.R
import com.harissabil.meakanu.data.local.entity.PlantEntity
import com.harissabil.meakanu.databinding.ItemHistoryBinding
import com.harissabil.meakanu.helper.MoreClicked
import com.harissabil.meakanu.helper.localiseDate

class HistoryListAdapter(
    private val historyList: List<PlantEntity>,
    private val moreClicked: MoreClicked
) :
    RecyclerView.Adapter<HistoryListAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = historyList[position]
        holder.binding.apply {
            tvDate.text = localiseDate(list.date!!)
            ivPlantImage.load(list.image)
            when (list.organ) {
                "leaf" -> {
                    chipOrgan.setTextColor(holder.itemView.context.getColor(R.color.leaf))
                }

                "flower" -> {
                    chipOrgan.setTextColor(holder.itemView.context.getColor(R.color.flower))
                }

                "fruit" -> {
                    chipOrgan.setTextColor(holder.itemView.context.getColor(R.color.fruit))
                }

                "bark" -> {
                    chipOrgan.setTextColor(holder.itemView.context.getColor(R.color.bark))
                }
            }
            chipOrgan.text = list.organ
            if (list.scientificNameWithoutAuthor != null) {
                tvPlantScientificWitoutAuthor.text = list.scientificNameWithoutAuthor
            } else {
                tvPlantScientificWitoutAuthor.text = "Undetermined species"
                tvPlantScientificWitoutAuthor.typeface = Typeface.DEFAULT
                tvPlantCommonName.visibility = View.GONE
            }
            tvPlantCommonName.text = if (list.commonName != null) list.commonName else ""

            cvMore.setOnClickListener {
                moreClicked.popUpMenu(it, list)
            }

            cvPlant.setOnClickListener {

                val mBundle = Bundle()
                mBundle.putParcelable("data", list)

                cvPlant.findNavController().navigate(
                    R.id.action_navigation_home_to_historyDetailFragment,
                    mBundle
                )
            }
        }
    }

}