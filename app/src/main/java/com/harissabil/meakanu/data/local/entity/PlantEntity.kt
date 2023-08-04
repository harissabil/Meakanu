package com.harissabil.meakanu.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "plant")
@Parcelize
data class PlantEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "organ")
    var organ: String? = null,

    @ColumnInfo(name = "scientific_name_without_author")
    var scientificNameWithoutAuthor: String? = null,

    @ColumnInfo(name = "common_name")
    var commonName: String? = null,

) : Parcelable