package com.harissabil.meakanu.data.remote.response.trefle

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResponseTrefle(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("meta")
	val meta: Meta,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null

) : Parcelable

@Parcelize
data class Meta(

	@field:SerializedName("total")
	val total: Int? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("family_common_name")
	val familyCommonName: String? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("genus_id")
	val genusId: Int? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("synonyms")
	val synonyms: List<String?>? = null,

	@field:SerializedName("scientific_name")
	val scientificName: String? = null,

	@field:SerializedName("bibliography")
	val bibliography: String? = null,

	@field:SerializedName("genus")
	val genus: String? = null,

	@field:SerializedName("rank")
	val rank: String? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("common_name")
	val commonName: String? = null,

	@field:SerializedName("family")
	val family: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

