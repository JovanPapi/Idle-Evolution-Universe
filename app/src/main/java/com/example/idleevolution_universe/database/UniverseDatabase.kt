package com.example.idleevolution_universe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.idleevolution_universe.dao.SectionDao
import com.example.idleevolution_universe.entity_model.Section

@Database(entities = [Section::class], version = 1)
abstract class UniverseDatabase : RoomDatabase() {
    abstract fun sectionDao(): SectionDao

    companion object {
        private var db: UniverseDatabase? = null
        fun getInstance(context: Context): UniverseDatabase {
            if (db == null) {
                db = create(context)
            }
            return db as UniverseDatabase
        }

        private fun create(context: Context): UniverseDatabase {
            return Room.databaseBuilder(
                context,
                UniverseDatabase::class.java,
                "universe_database"
            ).build()
        }
    }
}