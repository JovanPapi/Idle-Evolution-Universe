package com.example.idleevolution_universe.ui.ElementPopUpWindow

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
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.SectionElement
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UpgradeElementPopUpFragment : Fragment() {

    private var upgrade_element_image : ImageView? = null
    private var upgrade_requirements_text : TextView? = null
    private var upgrade_total_value_text : TextView? = null

    private var btn_close : Button? = null

    private var sectionRef = FirebaseDatabase.getInstance().reference
    private lateinit var curr_element : SectionElement

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upgrades_element_pop_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val element_dbKey = arguments?.getString("element_dbKey")
        val element_section = arguments?.getString("element_section")

        upgrade_requirements_text = view.findViewById(R.id.upgrade_section_requirements_text)
        upgrade_element_image = view.findViewById(R.id.upgrade_pop_up_image)
        upgrade_total_value_text = view.findViewById(R.id.upgrade_section_total_income_perSecond_text)
        btn_close = view.findViewById(R.id.upgrade_section_btn_close)

        // Check if the passed parameters for the current element are not null
        if (element_dbKey != null && element_section != null){
            // Get the table in database and make event listener to get the needed element
            sectionRef.child(element_section).addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChild(element_dbKey)){
                        curr_element = snapshot.child(element_dbKey).getValue(SectionElement::class.java)!!
                        upgrade_element_image?.setImageResource(curr_element.image)
                        upgrade_requirements_text?.text = "Cost: " + curr_element.currentCostUpgrade
                        upgrade_total_value_text?.text = "Total: " + curr_element.totalProductionAfterUpgrade + "/s"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Couldn't find the required element", Toast.LENGTH_LONG).show()
                }

            })
        }

        // Event for close button
        btn_close?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("section_name", element_section)
            findNavController().navigate(R.id.action_upgradeElementPopUpFragment_to_showUpgradeSectionElementsFragment, bundle)
        }
    }
}