package com.example.idleevolution_universe.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.idleevolution_universe.entity_model.Section
import kotlinx.coroutines.flow.Flow

@Dao
interface SectionDao {
    @Query("SELECT * FROM sections")
    fun getAll(): Flow<List<Section>>

    @Query("SELECT * FROM sections WHERE id IN(:sectionId)")
    fun getSectionById(sectionId: Int): Section

    @Insert
    fun updateSection(vararg section: Section)

    @Delete
    fun deleteSection(section: Section)
}