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
    var visible: Boolean = true
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
    /** The image of the element */
    var image: Int = 0,
    /** The section in which element is in */
    val section: String = "",
    /** The current cost of upgrading the element to produce more energy */
    var currentCostUpgrade: Int = 0,
    /** The total energy production calculated before upgrading the element */
    var energyProductionIncreaseAfterUpgrade: Int = 0,
    /** The total production calculated before upgrading the element */
    var totalProductionAfterUpgrade: Int = 0,
    /** Element quantity required for upgrade*/
    var requiredElementQuantity: Int = 0,
    /** Check if element is upgraded*/
    var checkIfElementIsUpgraded: Boolean? = false,
    /** The current energy production per second */
    var energyProductionPerSecond: Int = 0,
    /** How much is doubled, example x1, x2, x15, x300 */
    var productionPow: Int = 0,
    /** The key of the element in the realtime db */
    var dbKey: String = ""
)

fun createSection(dbKey: String, name: String, image: Int, visible: Boolean): Section {
    return Section(dbKey, name, image, visible)
}

fun createQuantumElements(): List<SectionElement> {
    return mutableListOf(
        SectionElement(
            "String",
            "The string theory assumes that the basic building blocks of matter are strings of magnitude 10-31 meters." +
                    " The original string theory, called the theory of boson strings, was established in 1970",
            R.drawable.element_string,
            "quantum",
            15,
            4,
            4,
            10,
        ),
        SectionElement(
            "Quantum foam",
            "Quantum foam is a concept in quantum mechanics devised by John Wheeler in 1955." +
                    "The foam is conceptualized as the foundation of the fabric of the Universe",
            R.drawable.element_quantum_foam,
            "quantum",
            54,
            15,
            15,
            20,
        ),
        SectionElement(
            "Neutrino",
            "Neutrino belongs to elementar particles. It's a fermion and its electrically neutral",
            R.drawable.element_neutrino,
            "quantum",
            162,
            44,
            44,
            25,
        ),
        SectionElement(
            "Higgs boson",
            "The Higgs boson is an elementary particle that was discovered during experiments conducted in the Large Hadron " +
                    "Collider in CERN",
            R.drawable.element_higgs_boson,
            "quantum",
            486,
            116,
            116,
            30,
        ),
        SectionElement(
            "Photon",
            "A photon is an elementary particle that does not have an electrical charge." +
                    " It's also a quanta of electromagnetic field, such as visible light",
            R.drawable.element_photon,
            "quantum",
            1458,
            301,
            301,
            35,
        ),
        SectionElement(
            "Quark",
            "A quark is a type of elementary particle and a fundamental constituent of matter. " +
                    "Quarks combine to form composite particles like protons and neutrons",
            R.drawable.element_quark,
            "quantum",
            4374,
            769,
            769,
            40,
        ),
        SectionElement(
            "Electron",
            " The electron is a subatomic particle, with a negative elementary electric charge",
            R.drawable.element_electron,
            "quantum",
            13122,
            1947,
            1947,
            45,
        ),
        SectionElement(
            "Neutron",
            "The neutron is a subatomic particle, with no net electric charge and a mass slightly" +
                    "larger than that of a proton. Protons and neutrons constitute the nuclei of atoms",
            R.drawable.element_neutron,
            "quantum",
            39366,
            4943,
            4943,
            50,
        ),
        SectionElement(
            "Proton", "A proton is a subatomic particle, with a positive electric charge and" +
                    "mass slightly less than that of a neutron. Protons and neutrons are collectively referred to as nucleons",
            R.drawable.element_proton,
            "quantum",
            118098,
            12604,
            12604,
            55,
        )
    )
}

fun createNanoElements(): List<SectionElement> {
    return mutableListOf(
        SectionElement(
            "Positron",
            "Positron is the antiparticle or the antimatter counterpart of the electron." +
                    "The positron has an electric charge of +1e and has the same mass as an electron",
            R.drawable.element_positron,
            "nano"
        ),
        SectionElement(
            "Nucleus",
            "The atomic nucleus is the small, dense region consisting of protons" +
                    "and neutrons at the center of an atom. Almost all of the mass of an atom is located in the nucleus",
            R.drawable.element_atomic_nucleus,
            "nano"
        ),
        SectionElement(
            "Atom",
            "An atom is the smallest constituent unit of ordinary matter. Every solid, liquid," +
                    "gas and plasma is composed of neutral or ionized atoms",
            R.drawable.element_atom,
            "nano"
        ),
        SectionElement(
            "Plasma",
            "Plasma is a state of matter resembling an ionised gas. It is" +
                    "One of the four fundamental states of matter, and was first described by chemist Irving Langmuir" +
                    "in the 1920s",
            R.drawable.element_plasma,
            "nano"
        ),
        SectionElement(
            "Sound wave",
            "Sound is a vibration that propagates as an audible wave of pressure, through gas," +
                    "liquid or solid. Humans can hear sound waves with frequencies between 20Hz and 20KHz",
            R.drawable.element_sound_wave,
            "nano"
        ),
        SectionElement(
            "Ion beam",
            "An ion beam is a type of charged particle beam consisting of ions. Some ion beams" +
                    "derive from the mercury vapor thrusters developed by NASA in the 1960s",
            R.drawable.element_ion_beam,
            "nano"
        ),
        SectionElement(
            "Isotope",
            "Isotopes are variant of a particular chemical element which differ in" +
                    "neutron number. All isotopes of a given element have the same number of protons in each atom",
            R.drawable.element_isotope,
            "nano"
        ),
        SectionElement(
            "Hydrogen", "Hydrogen is a chemical element with symbol H and atomic number 1. " +
                    "It is the lightest element on the periodic table, highly flammable",
            R.drawable.element_hydrogen,
            "nano"
        ),
        SectionElement(
            "Carbon", "Carbon is a chemical with symbol C and atomic number 6." +
                    "It is nonmetallic. Carbon is one of the few elements knows since antiquity",
            R.drawable.element_carbon,
            "nano"
        ),
        SectionElement(
            "Oxygen", "Oxygen is a chemical element with symbol O and atomic number 8." +
                    "It is highly reactive nonmetal. By mass, oxygen is the third-most abundant element in the universe",
            R.drawable.element_oxygen,
            "nano"
        ),
        SectionElement(
            "Iron", "Iron is a chemical element with symbol Fe. It is by mass the most common " +
                    "metal on Earth, forming much of Earth's outer and inner core",
            R.drawable.element_iron,
            "nano"
        ),
        SectionElement(
            "Xenon", "Xenon is a chemical element with symbol Xe. It is colorless, dense," +
                    "odorless noble gas found in the Earth's atmosphere in trace amounts",
            R.drawable.element_xenon,
            "nano"
        )
    )
}

fun createComplexElements(): List<SectionElement> {
    return mutableListOf(
        SectionElement(
            "Water", "Water is a transparent and colorless chemical substance that is the main" +
                    "constituent of Earth's lakes, oceans and the fluids of the most living organisms. Its chemical formula" +
                    "is H2O, meaning that its molecule contains one oxygen and two hydrogen atoms",
            R.drawable.element_water,
            "complex"
        ),
        SectionElement(
            "Salt", "Salt is a mineral composed of sodium chloride (NaCl). Salt is" +
                    "essential for life in general, and saltiness is one of the basic human tastes",
            R.drawable.element_salt,
            "complex"
        ),
        SectionElement(
            "Sand", "Sand is a naturally occuring granular material composed of finely" +
                    "diveded rock and mineral particles. The most common constituent of sand is silica (SiO2)",
            R.drawable.element_sand,
            "complex"
        ),
        SectionElement(
            "Lipid", "Lipid is a substance of biological origin. It comprises a group of " +
                    "molecules that include fats, waxes, sterols and fat-soluble vitamins. " +
                    "The main functions of lipids is storing energy",
            R.drawable.element_lipid,
            "complex"
        ),
        SectionElement(
            "Carbohydrate", "A carbohydrate is a biological molecule consisting of carbon (C)," +
                    "hydrogen (H) and oxygen (O) atoms. Carbohydrates perform numberous roles in living organism",
            R.drawable.element_carbohydrate,
            "complex"
        ),
        SectionElement(
            "Hemoglobin",
            "Hemoglobin is the iron-containing metalloprotein in the red blood cells." +
                    "Hemoglobin in the blood carries oxygen from lungs to the rest of the body",
            R.drawable.element_hemoglobin,
            "complex"
        ),
        SectionElement(
            "Dna", "DNA is a molecule that carries the genetic instructions used in the growth," +
                    "development, functioning and reproduction of all living organisms",
            R.drawable.element_dna,
            "complex"
        ),
        SectionElement(
            "Protein", "Proteins are large biomolecules, consisting of chains of amino" +
                    "acid residues. Proteins perform a vast array of functions within organisms",
            R.drawable.element_protein,
            "complex"
        ),
        SectionElement(
            "Chromosome",
            "A chromosome is a DNA molecule with part or all of the genetic material" +
                    "of an organism",
            R.drawable.element_chromosome,
            "complex"
        ),
        SectionElement(
            "Cell", "The cell is the basic structural, functional and biological unit" +
                    "of all known living organisms. A cell is the smalles unit of life that can replicate independently",
            R.drawable.element_cell,
            "complex"
        ),
        SectionElement(
            "Neuron",
            "A neuron is an electrically excitable cell that receives, processes and transmits" +
                    "information through electrical and chemical signals. Neurons are major components of the brain",
            R.drawable.element_neuron,
            "complex"
        ),
        SectionElement(
            "Microorganism",
            "A microorganism is a microscopic organism, which may be single-celled or a cluster of cells",
            R.drawable.element_microorganism,
            "complex"
        )
    )
}

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