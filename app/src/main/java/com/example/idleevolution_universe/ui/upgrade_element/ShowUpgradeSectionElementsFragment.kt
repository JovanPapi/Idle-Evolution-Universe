package com.example.idleevolution_universe.ui.upgrade_element

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.SectionElement
import com.example.idleevolution_universe.ui.adapter.UpgradeElementAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ShowUpgradeSectionElementsFragment : Fragment() {

    private val requirement_element_quantity: TextView? = null
    var btn_back: ImageButton? = null
    private val dbRef = FirebaseDatabase.getInstance().reference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_upgrades, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // In Bundle is stored the text of the button that is clicked and passed in this fragment
        val btn_clicked = arguments?.getString("section_name")?.toLowerCase()
        btn_back = view.findViewById(R.id.btnBack)
        btn_back?.setOnClickListener {
            findNavController().navigate(R.id.action_showUpgradeSectionElementsFragment_to_navigation_upgrades)
        }
        val sectionRef = FirebaseDatabase.getInstance().reference.child(btn_clicked!!)

        val recycler_view: RecyclerView = view.findViewById(R.id.upgrades_recyclerView)
        // Pass anonymous object of type 'ElementClicked' interface with overridden function ()
        val upgrade_section_elements_adapter =
            UpgradeElementAdapter(object : ElementClickedListenerInterface {
                override fun upgradeClickedElement(curr_element: SectionElement) {
                    if (curr_element.requiredElementQuantity > curr_element.productionPow) {
                        val tempNumber =
                            curr_element.requiredElementQuantity - curr_element.productionPow
                        Toast.makeText(
                            context,
                            "Lacking $tempNumber to ugprade ${curr_element.name}.",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }
                    val prevEnergyPerSec = curr_element.energyProductionPerSecond
                    curr_element.energyProductionPerSecond *= 5
                    curr_element.checkIfElementIsUpgraded = true
                    sectionRef.child(curr_element.dbKey).setValue(curr_element)

                    dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            var energyProduction =
                                Integer.parseInt(snapshot.child("energyProduction").value.toString())
                            energyProduction += (curr_element.energyProductionPerSecond - prevEnergyPerSec)
                            dbRef.child("energyProduction").setValue(energyProduction)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                        }

                    })
                    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! UNFINISHED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    Toast.makeText(context, "Element Upgraded Successfully", Toast.LENGTH_SHORT)
                        .show()
                }
            })

        // Access the database and list the elements from selected section each time we open this fragment

        sectionRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChildren()) {
                    val allElements = mutableListOf<SectionElement>()
                    for (item: DataSnapshot in snapshot.children) {
                        val currElement: SectionElement? = item.getValue(SectionElement::class.java)
                        // NEED TO REARANGE THE DATABASE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! UNFINISHED!!!!!!!!!!!!!!
                        if (currElement != null && currElement.checkIfElementIsUpgraded == false) {
                            currElement.dbKey = item.key.toString()
                            allElements.add(currElement)
                        }
                    }
                    upgrade_section_elements_adapter.submitList(allElements)
                    upgrade_section_elements_adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Couldn't find the database elements", Toast.LENGTH_SHORT)
                    .show()
            }

        })
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                recycler_view.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recycler_view.adapter = upgrade_section_elements_adapter

    }

    interface ElementClickedListenerInterface {
        fun upgradeClickedElement(curr_element: SectionElement)
    }
}