package com.harissabil.meakanu.data.remote.response.plantnet

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageResponsePlantnet(
	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("preferedReferential")
	val preferedReferential: String? = null,

	@field:SerializedName("bestMatch")
	val bestMatch: String? = null,

	@field:SerializedName("switchToProject")
	val switchToProject: String? = null,

	@field:SerializedName("query")
	val query: Query? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>,

	@field:SerializedName("version")
	val version: String? = null,

	@field:SerializedName("remainingIdentificationRequests")
	val remainingIdentificationRequests: Int? = null
) : Parcelable

@Parcelize
data class Gbif(

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class ResultsItem(

	@field:SerializedName("score")
	val score: Double? = null,

	@field:SerializedName("images")
	val images: List<ImagesItem>?,

	@field:SerializedName("species")
	val species: Species? = null,

	@field:SerializedName("gbif")
	val gbif: Gbif? = null
) : Parcelable

@Parcelize
data class Family(

	@field:SerializedName("scientificNameAuthorship")
	val scientificNameAuthorship: String? = null,

	@field:SerializedName("scientificName")
	val scientificName: String? = null,

	@field:SerializedName("scientificNameWithoutAuthor")
	val scientificNameWithoutAuthor: String? = null
) : Parcelable

@Parcelize
data class Url(

	@field:SerializedName("s")
	val s: String? = null,

	@field:SerializedName("m")
	val m: String? = null,

	@field:SerializedName("o")
	val o: String? = null
) : Parcelable

@Parcelize
data class Genus(

	@field:SerializedName("scientificNameAuthorship")
	val scientificNameAuthorship: String? = null,

	@field:SerializedName("scientificName")
	val scientificName: String? = null,

	@field:SerializedName("scientificNameWithoutAuthor")
	val scientificNameWithoutAuthor: String? = null
) : Parcelable

@Parcelize
data class Species(

	@field:SerializedName("commonNames")
	val commonNames: List<String?>? = null,

	@field:SerializedName("scientificNameAuthorship")
	val scientificNameAuthorship: String? = null,

	@field:SerializedName("scientificName")
	val scientificName: String? = null,

	@field:SerializedName("genus")
	val genus: Genus? = null,

	@field:SerializedName("scientificNameWithoutAuthor")
	val scientificNameWithoutAuthor: String? = null,

	@field:SerializedName("family")
	val family: Family? = null
) : Parcelable

@Parcelize
data class ImagesItem(

	@field:SerializedName("organ")
	val organ: String? = null,

	@field:SerializedName("date")
	val date: Date? = null,

	@field:SerializedName("license")
	val license: String? = null,

	@field:SerializedName("citation")
	val citation: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("url")
	val url: Url? = null
) : Parcelable

@Parcelize
data class Query(

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("organs")
	val organs: List<String?>? = null,

	@field:SerializedName("project")
	val project: String? = null,

	@field:SerializedName("includeRelatedImages")
	val includeRelatedImages: Boolean? = null
) : Parcelable

@Parcelize
data class Date(

	@field:SerializedName("string")
	val string: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: Int? = null
) : Parcelable
