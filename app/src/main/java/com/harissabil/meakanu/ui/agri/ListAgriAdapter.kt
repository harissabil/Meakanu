package com.harissabil.meakanu.ui.agri

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.appcompat.content.res.AppCompatResources
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
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
            holder.bind(data, holder.itemView.context)
        }
    }

    class MyViewHolder(private val binding: ItemAgriInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticlesItem, context: Context) {
            binding.tvDate.text = localiseeNewsDate(data.publishedAt)
            binding.ivAgriInfo.load(data.urlToImage) {
                placeholder(R.drawable.ic_launcher_foreground_splash)
                crossfade(true)
            }
            binding.tvTitle.text = data.title
            binding.tvSource.text = data.source.name.trim()

            binding.cvAgriInfo.setOnClickListener {
                @ColorInt val colorPrimary =
                    ContextCompat.getColor(context, R.color.green_700)
                val backIcon =
                    AppCompatResources.getDrawable(context, R.drawable.ic_arrow_back)!!.toBitmap()
                val customTabsIntent: CustomTabsIntent = CustomTabsIntent.Builder()
                    .setDefaultColorSchemeParams(
                        CustomTabColorSchemeParams.Builder()
                            .setToolbarColor(colorPrimary)
                            .build()
                    )
                    .setUrlBarHidingEnabled(false)
                    .setShowTitle(true)
                    .setCloseButtonIcon(backIcon)
                    .build()
                customTabsIntent.intent.putExtra(
                    Intent.EXTRA_REFERRER,
                    Uri.parse("android-app://" + context.packageName)
                )
                customTabsIntent.launchUrl(context, Uri.parse(data.url))
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