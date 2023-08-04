package com.harissabil.meakanu.data.remote.response.trefle

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class IdResponseTrefle(

    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
) : Parcelable

@Parcelize
data class Specifications(

    @field:SerializedName("growth_habit")
    val growthHabit: String? = null,

    @field:SerializedName("toxicity")
    val toxicity: Boolean? = null,

    @field:SerializedName("average_height")
    val averageHeight: AverageHeight? = null,

    @field:SerializedName("maximum_height")
    val maximumHeight: MaximumHeight? = null
) : Parcelable

@Parcelize
data class AverageHeight(

    @field:SerializedName("cm")
    val cm: Double? = null
) : Parcelable

@Parcelize
data class RowSpacing(

    @field:SerializedName("cm")
    val cm: Double? = null
) : Parcelable

@Parcelize
data class Genus(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("slug")
    val slug: String? = null
) : Parcelable

@Parcelize
data class SynonymsItem(

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable

@Parcelize
data class IntroducedItem(

    @field:SerializedName("species_count")
    val speciesCount: Int? = null,

    @field:SerializedName("tdwg_code")
    val tdwgCode: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("links")
    val links: Links? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("tdwg_level")
    val tdwgLevel: Int? = null,

    @field:SerializedName("slug")
    val slug: String? = null
) : Parcelable

@Parcelize
data class LeafItem(

    @field:SerializedName("copyright")
    val copyright: String? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable

@Parcelize
data class BarkItem(

    @field:SerializedName("copyright")
    val copyright: String? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable

@Parcelize
data class MinimumRootDepth(

    @field:SerializedName("cm")
    val cm: Double? = null
) : Parcelable

@Parcelize
data class Data(

    @field:SerializedName("main_species_id")
    val mainSpeciesId: Int? = null,

    @field:SerializedName("family_common_name")
    val familyCommonName: String? = null,

    @field:SerializedName("year")
    val year: Int? = null,

    @field:SerializedName("genus_id")
    val genusId: Int? = null,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("scientific_name")
    val scientificName: String? = null,

    @field:SerializedName("vegetable")
    val vegetable: Boolean? = null,

    @field:SerializedName("bibliography")
    val bibliography: String? = null,

    @field:SerializedName("varieties")
    val varieties: List<VarietiesItem?>? = null,

    @field:SerializedName("genus")
    val genus: Genus,

    @field:SerializedName("species")
    val species: List<SpeciesItem?>? = null,

    @field:SerializedName("observations")
    val observations: String? = null,

    @field:SerializedName("links")
    val links: Links? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("common_name")
    val commonName: String? = null,

    @field:SerializedName("family")
    val family: Family,

    @field:SerializedName("subspecies")
    val subspecies: List<SubspeciesItem?>? = null,

    @field:SerializedName("main_species")
    val mainSpecies: MainSpecies,

    @field:SerializedName("slug")
    val slug: String? = null,

    ) : Parcelable

@Parcelize
data class Distribution(

    @field:SerializedName("introduced")
    val introduced: List<String>? = null,

    @field:SerializedName("native")
    val native: List<String>? = null
) : Parcelable

@Parcelize
data class SpeciesItem(

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
    val id: Int? = null,

    @field:SerializedName("common_name")
    val commonName: String? = null,

    @field:SerializedName("family")
    val family: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable

@Parcelize
data class MaximumHeight(

    @field:SerializedName("cm")
    val cm: Double? = null
) : Parcelable

@Parcelize
data class OtherItem(

    @field:SerializedName("copyright")
    val copyright: String? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable

@Parcelize
data class MainSpecies(

    @field:SerializedName("family_common_name")
    val familyCommonName: String? = null,

    @field:SerializedName("year")
    val year: Int? = null,

    @field:SerializedName("genus_id")
    val genusId: Int? = null,

    @field:SerializedName("vegetable")
    val vegetable: Boolean? = null,

    @field:SerializedName("distribution")
    val distribution: Distribution,

    @field:SerializedName("specifications")
    val specifications: Specifications? = null,

    @field:SerializedName("bibliography")
    val bibliography: String? = null,

    @field:SerializedName("observations")
    val observations: String? = null,

    @field:SerializedName("rank")
    val rank: String? = null,

    @field:SerializedName("common_names")
    val commonNames: CommonNames? = null,

    @field:SerializedName("links")
    val links: Links? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("common_name")
    val commonName: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("synonyms")
    val synonyms: List<SynonymsItem?>? = null,

    @field:SerializedName("scientific_name")
    val scientificName: String? = null,

    @field:SerializedName("distributions")
    val distributions: Distributions? = null,

    @field:SerializedName("genus")
    val genus: String? = null,

    @field:SerializedName("edible")
    val edible: Boolean? = null,

    @field:SerializedName("growth")
    val growth: Growth,

    @field:SerializedName("family")
    val family: String? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable

@Parcelize
data class HabitItem(

    @field:SerializedName("copyright")
    val copyright: String? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable

@Parcelize
data class SubspeciesItem(

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
    val id: Int? = null,

    @field:SerializedName("common_name")
    val commonName: String? = null,

    @field:SerializedName("family")
    val family: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable

@Parcelize
data class CommonNames(

    @field:SerializedName("swe")
    val swe: List<String?>? = null,

    @field:SerializedName("nob")
    val nob: List<String?>? = null,

    @field:SerializedName("nn")
    val nn: List<String?>? = null,

    @field:SerializedName("de")
    val de: List<String?>? = null,

    @field:SerializedName("no")
    val no: List<String?>? = null,

    @field:SerializedName("isl")
    val isl: List<String?>? = null,

    @field:SerializedName("fi")
    val fi: List<String?>? = null,

    @field:SerializedName("pt")
    val pt: List<String?>? = null,

    @field:SerializedName("fin")
    val fin: List<String?>? = null,

    @field:SerializedName("fr")
    val fr: List<String?>? = null,

    @field:SerializedName("dan")
    val dan: List<String?>? = null,

    @field:SerializedName("nor")
    val nor: List<String?>? = null,

    @field:SerializedName("por")
    val por: List<String?>? = null,

    @field:SerializedName("fra")
    val fra: List<String?>? = null,

    @field:SerializedName("deu")
    val deu: List<String?>? = null,

    @field:SerializedName("sk")
    val sk: List<String?>? = null,

    @field:SerializedName("ca")
    val ca: List<String?>? = null,

    @field:SerializedName("eng")
    val eng: List<String?>? = null,

    @field:SerializedName("sv")
    val sv: List<String?>? = null,

    @field:SerializedName("nld")
    val nld: List<String?>? = null,

    @field:SerializedName("en")
    val en: List<String?>? = null,

    @field:SerializedName("it")
    val it: List<String?>? = null,

    @field:SerializedName("nno")
    val nno: List<String?>? = null,

    @field:SerializedName("es")
    val es: List<String?>? = null,

    @field:SerializedName("cs")
    val cs: List<String?>? = null,

    @field:SerializedName("nb")
    val nb: List<String?>? = null,

    @field:SerializedName("cym")
    val cym: List<String?>? = null,

    @field:SerializedName("cy")
    val cy: List<String?>? = null,

    @field:SerializedName("da")
    val da: List<String?>? = null,

    @field:SerializedName("he")
    val he: List<String?>? = null,

    @field:SerializedName("nl")
    val nl: List<String?>? = null
) : Parcelable

@Parcelize
data class VarietiesItem(

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
    val synonyms: List<String>? = null,

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
    val id: Int? = null,

    @field:SerializedName("common_name")
    val commonName: String? = null,

    @field:SerializedName("family")
    val family: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable

@Parcelize
data class FruitItem(

    @field:SerializedName("copyright")
    val copyright: String? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable

@Parcelize
data class Distributions(

    @field:SerializedName("introduced")
    val introduced: List<IntroducedItem?>? = null,

    @field:SerializedName("native")
    val jsonMemberNative: List<JsonMemberNativeItem?>? = null
) : Parcelable

@Parcelize
data class Family(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("links")
    val links: Links? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("common_name")
    val commonName: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null
) : Parcelable

@Parcelize
data class Links(

    @field:SerializedName("genus")
    val genus: String? = null,

    @field:SerializedName("plant")
    val plant: String? = null,

    @field:SerializedName("self")
    val self: String? = null,

    @field:SerializedName("division_order")
    val divisionOrder: String? = null,

    @field:SerializedName("species")
    val species: String? = null,

    @field:SerializedName("plants")
    val plants: String? = null,

    @field:SerializedName("family")
    val family: String? = null
) : Parcelable

@Parcelize
data class MinimumTemperature(

    @field:SerializedName("deg_c")
    val degC: Double? = null,

    @field:SerializedName("deg_f")
    val degF: Double? = null
) : Parcelable

@Parcelize
data class Growth(

    @field:SerializedName("row_spacing")
    val rowSpacing: RowSpacing? = null,

    @field:SerializedName("maximum_temperature")
    val maximumTemperature: MaximumTemperature? = null,

    @field:SerializedName("soil_humidity")
    val soilHumidity: Int? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("days_to_harvest")
    val daysToHarvest: Int? = null,

    @field:SerializedName("ph_maximum")
    val phMaximum: Double? = null,

    @field:SerializedName("bloom_months")
    val bloomMonths: List<String>? = null,

    @field:SerializedName("atmospheric_humidity")
    val atmosphericHumidity: Int? = null,

    @field:SerializedName("soil_salinity")
    val soilSalinity: Int? = null,

    @field:SerializedName("fruit_months")
    val fruitMonths: List<String>? = null,

    @field:SerializedName("maximum_precipitation")
    val maximumPrecipitation: MaximumPrecipitation? = null,

    @field:SerializedName("light")
    val light: Int? = null,

    @field:SerializedName("ph_minimum")
    val phMinimum: Double? = null,

    @field:SerializedName("growth_months")
    val growthMonths: List<Int>? = null,

    @field:SerializedName("soil_nutriments")
    val soilNutriments: Int? = null,

    @field:SerializedName("minimum_temperature")
    val minimumTemperature: MinimumTemperature? = null,

    @field:SerializedName("minimum_root_depth")
    val minimumRootDepth: MinimumRootDepth? = null
) : Parcelable

@Parcelize
data class FlowerItem(

    @field:SerializedName("copyright")
    val copyright: String? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable

@Parcelize
data class MaximumPrecipitation(

    @field:SerializedName("mm")
    val mm: Double? = null
) : Parcelable

@Parcelize
data class JsonMemberNativeItem(

    @field:SerializedName("species_count")
    val speciesCount: Int? = null,

    @field:SerializedName("tdwg_code")
    val tdwgCode: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("links")
    val links: Links? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("tdwg_level")
    val tdwgLevel: Int? = null,

    @field:SerializedName("slug")
    val slug: String? = null
) : Parcelable

@Parcelize
data class MaximumTemperature(

    @field:SerializedName("deg_c")
    val degC: Double? = null,

    @field:SerializedName("deg_f")
    val degF: Double? = null
) : Parcelable
