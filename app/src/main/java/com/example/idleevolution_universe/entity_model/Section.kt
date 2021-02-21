package com.example.idleevolution_universe.entity_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.idleevolution_universe.R
import java.util.*

@Entity(tableName = "sections")
data class Section(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: Int,
    @ColumnInfo(name = "visible") val visible: Boolean
)

object SectionData {
    val sections = listOf<Section>(
        Section(
            UUID.randomUUID().toString(),
            "Quantum",
            R.drawable.img_nuclear_energy_background,
            true
        ),
        Section(
            UUID.randomUUID().toString(),
            "Quantum",
            R.drawable.img_nuclear_energy_background,
            false
        ),
        Section(
            UUID.randomUUID().toString(),
            "Quantum",
            R.drawable.img_nuclear_energy_background,
            false
        ),
        Section(
            UUID.randomUUID().toString(),
            "Quantum",
            R.drawable.img_nuclear_energy_background,
            false
        ),
        Section(
            UUID.randomUUID().toString(),
            "Quantum",
            R.drawable.img_nuclear_energy_background,
            false
        ),
        Section(
            UUID.randomUUID().toString(),
            "Quantum",
            R.drawable.img_nuclear_energy_background,
            false
        )
    )
}