package com.harissabil.meakanu.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harissabil.meakanu.data.local.entity.PlantEntity

@Database(
    entities = [PlantEntity::class],
    version = 1,
    exportSchema = true
)
abstract class PlantDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao

    companion object {
        @Volatile
        private var INSTANCE: PlantDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): PlantDatabase {
            if (INSTANCE == null) {
                synchronized(PlantDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PlantDatabase::class.java,
                        "plant_database"
                    ).build()
                }
            }
            return INSTANCE as PlantDatabase
        }
    }
}