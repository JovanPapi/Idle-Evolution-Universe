package com.example.idleevolution_universe.entity_model

import com.example.idleevolution_universe.R

data class Section(
    /** The database key stored and loaded from the table in firebase database */
    var dbKey: String = "",
    /** The name of the section */
    val name: String = "",
    /** The image of the section */
    val image: Int = 0,
    /** This property shows if the section should be visible or not on the UI */
    var visible: Boolean = true,
    /** The elements that contains specific section */
    val elements: List<SectionElement> = emptyList()
)

data class SectionElement(
    /** The name of the section element */
    val name: String = "",
    /** The description of the section element */
    val description: String = "",
    /** How much is doubled, example x1, x2, x15, x300 */
    var productionPow: Int = 0,
    /** The current cost of upgrading the element to produce more energy */
    var currentCostUpgrade: Long = 0,
    /** The total production calculated before upgrading the element */
    var totalProductionAfterUpgrade: Long = 0,
    /** The total energy production calculated before upgrading the element */
    var energyProductionIncreaseAfterUpgrade: Int = 0,
    /** The current energy production per second */
    var energyProductionPerSecond: Long = 0
)

fun createSection(dbKey: String, name: String, image: Int, visible: Boolean): Section {
    return Section(dbKey, name, image, visible, createSectionElements(name))
}

fun createSectionElements(sectionName: String): List<SectionElement> {
    val quantumArray = listOf("String", "Quantum foam", "Neutrino", "Higgs boson", "Photon", "Quark", "Electron", "Neutron", "Proton")
    //TODO create all other section elements and store them in arrays
    return when (sectionName) {
        "Quantum" -> quantumArray.map { SectionElement(it) }
        else -> emptyList()
    }
}

object SectionData {
    val sections = listOf(
        createSection("", "Quantum", R.drawable.quantum, true,),
        createSection("", "Nano", R.drawable.quantum, true),
        createSection("", "Complex", R.drawable.quantum, true),
        createSection("", "Bio", R.drawable.quantum, false),
        createSection("", "Flora", R.drawable.quantum, false),
        createSection("", "Landscape", R.drawable.quantum, false),
        createSection("", "Fauna", R.drawable.quantum, false),
        createSection("", "Construction", R.drawable.quantum, false),
        createSection("", "Cosmos", R.drawable.quantum, false),
        createSection("", "Space", R.drawable.quantum, false),
        createSection("", "Planets", R.drawable.quantum, false),
        createSection("", "Universe", R.drawable.quantum, false)
    )
}