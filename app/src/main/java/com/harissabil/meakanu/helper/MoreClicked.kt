package com.harissabil.meakanu.helper

import android.view.View
import com.harissabil.meakanu.data.local.entity.PlantEntity

interface MoreClicked {
    fun popUpMenu(view: View, plant: PlantEntity)
}