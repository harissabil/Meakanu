package com.harissabil.meakanu.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.harissabil.meakanu.data.local.entity.PlantEntity

@Dao
interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(plant: PlantEntity)

    @Update
    suspend fun update(plant: PlantEntity)

    @Delete
    suspend fun delete(plant: PlantEntity)

    @Query("SELECT * from plant ORDER BY id DESC")
    fun getLatest(): LiveData<List<PlantEntity>>

    @Query("SELECT * FROM plant ORDER BY id DESC LIMIT 1")
    suspend fun getLastRow(): PlantEntity

}