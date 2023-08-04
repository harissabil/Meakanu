package com.harissabil.meakanu.ui.agri

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AgriInfo(
    val title: String,
    val source: String,
    val link: String,
) : Parcelable
