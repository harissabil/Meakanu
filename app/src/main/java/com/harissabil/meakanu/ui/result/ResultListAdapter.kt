package com.harissabil.meakanu.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.harissabil.meakanu.R
import com.harissabil.meakanu.data.remote.response.plantnet.ResultsItem
import com.harissabil.meakanu.databinding.ItemResultBinding
import com.harissabil.meakanu.helper.ConfirmButton

class ResultListAdapter(
    private val resultList: List<ResultsItem>,
    private val confirmButton: ConfirmButton
) :
    RecyclerView.Adapter<ResultListAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = if (resultList.size > 5) 5 else resultList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = resultList[position]
        holder.binding.apply {
            val scientificNameWithoutAuthor = list.species?.scientificNameWithoutAuthor ?: "-"
            tvPlantScientificWitoutAuthor.text = scientificNameWithoutAuthor
            val commonName = list.species?.commonNames?.firstOrNull() ?: "-"
            tvPlantCommonName.text = commonName
            val accuracy = list.score?.times(100)?.toInt()
            tvPlantAccuracy.text =
                if (accuracy != null) {
                    if (accuracy < 1) {
                        "<1%"
                    } else {
                        "$accuracy%"
                    }
                } else "-"

            btnSearch.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("query", list.species?.scientificNameWithoutAuthor)

                btnSearch.findNavController().navigate(
                    R.id.action_resultFragment_to_plantWithoutBottomNavFragment,
                    bundle
                )
            }

            btnConfirm.setOnClickListener {
                confirmButton.confirm(scientificNameWithoutAuthor, commonName)
            }
        }
    }
}