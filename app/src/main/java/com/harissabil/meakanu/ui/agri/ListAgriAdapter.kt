package com.harissabil.meakanu.ui.agri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.harissabil.meakanu.R
import com.harissabil.meakanu.data.remote.response.news.ArticlesItem
import com.harissabil.meakanu.databinding.ItemAgriInfoBinding
import com.harissabil.meakanu.helper.localiseeNewsDate

class ListAgriAdapter :
    PagingDataAdapter<ArticlesItem, ListAgriAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemAgriInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class MyViewHolder(private val binding: ItemAgriInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticlesItem) {
            binding.tvDate.text = localiseeNewsDate(data.publishedAt)
            binding.ivAgriInfo.load(data.urlToImage) {
                placeholder(R.drawable.placeholder_meakanu)
                crossfade(true)
            }
            binding.tvTitle.text = data.title
            binding.tvSource.text = data.source.name.trim()

            binding.cvAgriInfo.setOnClickListener {
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
//                binding.root.context.startActivity(intent)
                val bundle = Bundle()
                bundle.putString("url", data.url)
                bundle.putString("title", data.title)

                binding.cvAgriInfo.findNavController().navigate(
                    R.id.action_navigation_agri_to_newsDetailFragment,
                    bundle
                )
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}