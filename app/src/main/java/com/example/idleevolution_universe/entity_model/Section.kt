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
//    /** The elements that contains specific section */
//    val elements: List<SectionElement> = emptyList(),
//    /** The total number of elements unlocked in order to unlock the next section */
//    var elementsUnlocked: Int = 0
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
    return Section(dbKey, name, image, visible)
}

fun createQuantumElements(): List<SectionElement> {
    return mutableListOf(
        SectionElement(
            "String",
            "The string theory assumes that the basic building blocks of matter are strings of magnitude 10-31 meters." +
                    " The original string theory, called the theory of boson strings, was established in 1970"
        ),
        SectionElement(
            "Quantum foam",
            "Quantum foam is a concept in quantum mechanics devised by John Wheeler in 1955." +
                    "The foam is conceptualized as the foundation of the fabric of the Universe"
        ),
        SectionElement(
            "Neutrino",
            "Neutrino belongs to elementar particles. It's a fermion and its electrically neutral"
        ),
        SectionElement(
            "Higgs boson",
            "The Higgs boson is an elementary particle that was discovered during experiments conducted in the Large Hadron Collider in CERN"
        ),
        SectionElement(
            "Photon",
            "A photon is an elementary particle that does not have an electrical charge." +
                    " It's also a quanta of electromagnetic field, such as visible light"
        ),
        SectionElement(
            "Quark",
            "A quark is a type of elementary particle and a fundamental constituent of matter. " +
                    "Quarks combine to form composite particles like protons and neutrons"
        ),
        SectionElement(
            "Electron",
            " The electron is a subatomic particle, with a negative elementary electric charge"
        ),
        SectionElement(
            "Neutron",
            "The neutron is a subatomic particle, with no net electric charge and a mass slightly" +
                    "larger than that of a proton. Protons and neutrons constitute the nuclei of atoms"
        ),
        SectionElement(
            "Proton", "A proton is a subatomic particle, with a positive electric charge and" +
                    "mass slightly less than that of a neutron. Protons and neutrons are collectively referred to as nucleons"
        )

    )
}

fun createNanoElements(): List<SectionElement> {
    return mutableListOf(
        SectionElement(
            "Positron",
            "Positron is the antiparticle or the antimatter counterpart of the electron." +
                    "The positron has an electric charge of +1e and has the same mass as an electron"
        ),
        SectionElement(
            "Nucleus", "The atomic nucleus is the small, dense region consisting of protons" +
                    "and neutrons at the center of an atom. Almost all of the mass of an atom is located in the nucleus"
        ),
        SectionElement(
            "Atom",
            "An atom is the smallest constituent unit of ordinary matter. Every solid, liquid," +
                    "gas and plasma is composed of neutral or ionized atoms"
        ),
        SectionElement(
            "Plasma", "Plasma is a state of matter resembling an ionised gas. It is" +
                    "One of the four fundamental states of matter, and was first described by chemist Irving Langmuir" +
                    "in the 1920s"
        ),
        SectionElement(
            "Sound wave",
            "Sound is a vibration that propagates as an audible wave of pressure, through gas," +
                    "liquid or solid. Humans can hear sound waves with frequencies between 20Hz and 20KHz"
        ),
        SectionElement(
            "Ion beam",
            "An ion beam is a type of charged particle beam consisting of ions. Some ion beams" +
                    "derive from the mercury vapor thrusters developed by NASA in the 1960s"
        ),
        SectionElement(
            "Isotope", "Isotopes are variant of a particular chemical element which differ in" +
                    "neutron number. All isotopes of a given element have the same number of protons in each atom"
        ),
        SectionElement(
            "Hydrogen", "Hydrogen is a chemical element with symbol H and atomic number 1. " +
                    "It is the lightest element on the periodic table, highly flammable"
        ),
        SectionElement(
            "Carbon", "Carbon is a chemical with symbol C and atomic number 6." +
                    "It is nonmetallic. Carbon is one of the few elements knows since antiquity"
        ),
        SectionElement(
            "Oxygen", "Oxygen is a chemical element with symbol O and atomic number 8." +
                    "It is highly reactive nonmetal. By mass, oxygen is the third-most abundant element in the universe"
        ),
        SectionElement(
            "Iron", "Iron is a chemical element with symbol Fe. It is by mass the most common " +
                    "metal on Earth, forming much of Earth's outer and inner core"
        ),
        SectionElement(
            "Xenon", "Xenon is a chemical element with symbol Xe. It is colorless, dense," +
                    "odorless noble gas found in the Earth's atmosphere in trace amounts"
        )
    )
}

fun createComplexElements(): List<SectionElement> {
    return mutableListOf(
        SectionElement(
            "Water", "Water is a transparent and colorless chemical substance that is the main" +
                    "constituent of Earth's lakes, oceans and the fluids of the most living organisms. Its chemical formula" +
                    "is H2O, meaning that its molecule contains one oxygen and two hydrogen atoms"
        ),
        SectionElement(
            "Salt", "Salt is a mineral composed of sodium chloride (NaCl). Salt is" +
                    "essential for life in general, and saltiness is one of the basic human tastes"
        ),
        SectionElement(
            "Sand", "Sand is a naturally occuring granular material composed of finely" +
                    "diveded rock and mineral particles. The most common constituent of sand is silica (SiO2)"
        ),
        SectionElement(
            "Lipid", "Lipid is a substance of biological origin. It comprises a group of " +
                    "molecules that include fats, waxes, sterols and fat-soluble vitamins. The main functions of lipids is storing energy"
        ),
        SectionElement(
            "Carbohydrate", "A carbohydrate is a biological molecule consisting of carbon (C)," +
                    "hydrogen (H) and oxygen (O) atoms. Carbohydrates perform numberous roles in living organism"
        ),
        SectionElement(
            "Hemoglobin",
            "Hemoglobin is the iron-containing metalloprotein in the red blood cells." +
                    "Hemoglobin in the blood carries oxygen from lungs to the rest of the body"
        ),
        SectionElement(
            "Dna", "DNA is a molecule that carries the genetic instructions used in the growth," +
                    "development, functioning and reproduction of all living organisms"
        ),
        SectionElement(
            "Protein", "Proteins are large biomolecules, consisting of chains of amino" +
                    "acid residues. Proteins perform a vast array of functions within organisms"
        ),
        SectionElement(
            "Chromosome",
            "A chromosome is a DNA molecule with part or all of the genetic material" +
                    "of an organism"
        ),
        SectionElement(
            "Cell", "The cell is the basic structural, functional and biological unit" +
                    "of all known living organisms. A cell is the smalles unit of life that can replicate independently"
        ),
        SectionElement(
            "Neuron",
            "A neuron is an electrically excitable cell that receives, processes and transmits" +
                    "information through electrical and chemical signals. Neurons are major components of the brain"
        ),
        SectionElement(
            "Microorganism",
            "A microorganism is a microscopic organism, which may be single-celled or a cluster of cells"
        )
    )
}

//fun createSectionElements(sectionName: String): List<SectionElement> {
//
//
//    //TODO create all other section elements and store them in arrays
////    return when (sectionName) {
////        "Quantum" -> quantumElements.map { SectionElement(it) }
////        "Nano" -> nanoElements.map { SectionElement(it) }
////        "Complex" -> complexElements.map { SectionElement(it) }
////        else -> emptyList()
////    }
//}

object SectionData {
    var sections = mutableListOf(
        createSection("", "Quantum", R.drawable.quantum, true),
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

object SectionElements {
    val sectionElements = mutableListOf(
        createQuantumElements(),
        createNanoElements(),
        createComplexElements()
    )
}