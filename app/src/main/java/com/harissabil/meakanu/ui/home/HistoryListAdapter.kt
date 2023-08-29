package com.harissabil.meakanu.ui.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.harissabil.meakanu.R
import com.harissabil.meakanu.data.local.entity.PlantEntity
import com.harissabil.meakanu.databinding.ItemHistoryBinding
import com.harissabil.meakanu.helper.MoreClicked
import com.harissabil.meakanu.helper.localiseDate

class HistoryListAdapter(
    private val moreClicked: MoreClicked
) :
    ListAdapter<PlantEntity, HistoryListAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<PlantEntity>() {

        override fun areItemsTheSame(oldItem: PlantEntity, newItem: PlantEntity):
                Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlantEntity, newItem: PlantEntity):
                Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: PlantEntity, moreClicked: MoreClicked) {
            binding.apply {
                tvDate.text = localiseDate(item.date!!)
                ivPlantImage.load(item.image)
                when (item.organ) {
                    "leaf" -> {
                        chipOrgan.setTextColor(itemView.context.getColor(R.color.leaf))
                    }

                    "flower" -> {
                        chipOrgan.setTextColor(itemView.context.getColor(R.color.flower))
                    }

                    "fruit" -> {
                        chipOrgan.setTextColor(itemView.context.getColor(R.color.fruit))
                    }

                    "bark" -> {
                        chipOrgan.setTextColor(itemView.context.getColor(R.color.bark))
                    }
                }
                chipOrgan.text = item.organ
                if (item.scientificNameWithoutAuthor != null) {
                    tvPlantScientificWitoutAuthor.text = item.scientificNameWithoutAuthor
                } else {
                    tvPlantScientificWitoutAuthor.text = "Undetermined species"
                    tvPlantScientificWitoutAuthor.typeface = Typeface.DEFAULT
                    tvPlantCommonName.visibility = View.GONE
                }
                tvPlantCommonName.text = if (item.commonName != null) item.commonName else ""

                cvMore.setOnClickListener {
                    moreClicked.popUpMenu(it, item)
                }

                cvPlant.setOnClickListener {

                    val mBundle = Bundle()
                    mBundle.putParcelable("data", item)

                    cvPlant.findNavController().navigate(
                        R.id.action_navigation_home_to_historyDetailFragment,
                        mBundle
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), moreClicked)
    }

}