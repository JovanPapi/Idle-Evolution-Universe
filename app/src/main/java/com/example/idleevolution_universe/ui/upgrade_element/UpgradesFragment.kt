package com.example.idleevolution_universe.ui.upgrade_element

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.SectionElement
import com.example.idleevolution_universe.ui.adapter.UpgradeElementAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UpgradesFragment : Fragment() {

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

        val quantumRef = FirebaseDatabase.getInstance().reference.child("quantum")
        val nanoRef = FirebaseDatabase.getInstance().reference.child("nano")
        val complexRef = FirebaseDatabase.getInstance().reference.child("complex")

        val recyclerView: RecyclerView = view.findViewById(R.id.upgrades_recyclerView)
        // Pass anonymous object of type 'ElementClicked' interface with overridden function ()
        val upgradeSectionElementsAdapter =
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
                    curr_element.totalProductionAfterUpgrade *= 5
                    curr_element.checkIfElementIsUpgraded = true

                    if (curr_element.section == "quantum") {
                        quantumRef.child(curr_element.dbKey).setValue(curr_element)
                    } else if (curr_element.section == "complex") {
                        complexRef.child(curr_element.dbKey).setValue(curr_element)
                    } else {
                        nanoRef.child(curr_element.dbKey).setValue(curr_element)
                    }


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


        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = mutableListOf<SectionElement>()
                for (item: DataSnapshot in snapshot.children) {
                    if (item.hasChildren()) {
                        for (element: DataSnapshot in item.children.reversed()) {
                            val currElement: SectionElement? =
                                element.getValue(SectionElement::class.java)
                            if (currElement != null && currElement.checkIfElementIsUpgraded == false) {
                                currElement.dbKey = element.key.toString()
                                result.add(currElement)
                            }
                        }
                    }
                }
                upgradeSectionElementsAdapter.submitList(result.asReversed())
                upgradeSectionElementsAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = upgradeSectionElementsAdapter

    }

    interface ElementClickedListenerInterface {
        fun upgradeClickedElement(curr_element: SectionElement)
    }
}