package com.example.idleevolution_universe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.idleevolution_universe.MainActivity
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.SectionElement
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ElementPopupFragment : Fragment() {
    private var imageView: ImageView? = null
    private var costTv: TextView? = null
    private var totalTV: TextView? = null
    private var increaseTV: TextView? = null
    private var descriptionTV: TextView? = null
    private var btnClosePopUp: Button? = null
    private var btnUpgradeElement: Button? = null
    private val sectionRef = FirebaseDatabase.getInstance().reference
    private lateinit var element: SectionElement

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_element_popup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val elementDbKey = arguments?.getString("elementDbKey")
        val sectionElement = arguments?.getString("sectionElement")

        costTv = view.findViewById(R.id.costTV)
        totalTV = view.findViewById(R.id.productionAfterUpgradeTV)
        increaseTV = view.findViewById(R.id.increaseTV)
        imageView = view.findViewById(R.id.popUpImage)
        descriptionTV = view.findViewById(R.id.descriptionTV)

        btnClosePopUp = view.findViewById(R.id.btnClosePopUp)
        btnUpgradeElement = view.findViewById(R.id.btnUpgradeElement)


        if (sectionElement != null && elementDbKey != null) {
            sectionRef.child(sectionElement).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChild(elementDbKey)) {
                        element =
                            snapshot.child(elementDbKey).getValue(SectionElement::class.java)!!
                        imageView?.setImageResource(element.image)
                        costTv?.text = "Cost: " + String.format("%,d", element.currentCostUpgrade)
                        descriptionTV?.text = "Description: " + element.description
                        totalTV?.text = "Total: " + String.format("%,d", element.totalProductionAfterUpgrade) + "/s"
                        increaseTV?.text =
                            "Increase: " + String.format("%,d", element.energyProductionIncreaseAfterUpgrade) + "/s"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnClosePopUp?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("sectionName", arguments?.getString("sectionName"))
            findNavController().navigate(
                R.id.action_elementPopUpActivity_to_showSectionElementsFragment,
                bundle
            )
        }
        btnUpgradeElement?.setOnClickListener {
            val currentEnergyAmount = MainActivity.tvUserCurrentEnergy?.text.toString().replace(",", "").toInt()
            if (currentEnergyAmount < element.currentCostUpgrade) {
                Toast.makeText(context, "Not enough energy!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                MainActivity.tvUserCurrentEnergy?.text =
                    (currentEnergyAmount - element.currentCostUpgrade).toString()
                element.totalProductionAfterUpgrade += element.energyProductionIncreaseAfterUpgrade
                element.energyProductionPerSecond += element.energyProductionIncreaseAfterUpgrade
                element.productionPow++
                element.currentCostUpgrade += (element.currentCostUpgrade * 0.3).toInt()

                sectionRef.child(sectionElement!!).child(elementDbKey!!).child("currentCostUpgrade")
                    .setValue(element.currentCostUpgrade)
                sectionRef.child(sectionElement).child(elementDbKey)
                    .child("totalProductionAfterUpgrade")
                    .setValue(element.totalProductionAfterUpgrade)
                sectionRef.child(sectionElement).child(elementDbKey).child("productionPow")
                    .setValue(element.productionPow)
                sectionRef.child(sectionElement).child(elementDbKey)
                    .child("energyProductionPerSecond")
                    .setValue(element.energyProductionPerSecond)

                sectionRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var energyProduction = snapshot.child("energyProduction").value.toString().toInt()
                        energyProduction += element.energyProductionIncreaseAfterUpgrade
                        sectionRef.child("energyProduction").setValue(energyProduction)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }

                })
            }

        }
    }
}