package com.example.idleevolution_universe.ui.upgrade_element

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.Section
import com.example.idleevolution_universe.entity_model.SectionData
import com.example.idleevolution_universe.entity_model.SectionElement
import com.example.idleevolution_universe.entity_model.SectionElements
import com.example.idleevolution_universe.ui.adapter.UpgradeElementAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ShowUpgradeSectionElementsFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_upgrades, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // In Bundle is stored the text of the button that is clicked and passed in this fragment
        val btn_clicked = arguments?.getString("section_name")?.toLowerCase()
        val sectionRef = FirebaseDatabase.getInstance().reference.child(btn_clicked!!)

        val recycler_view : RecyclerView = view.findViewById(R.id.upgrades_recyclerView)

        // Access the database and list the elements from selected section each time we open this fragment
        val allElements = mutableListOf<SectionElement>()
        sectionRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.hasChildren()){
                    for (item : DataSnapshot in snapshot.children){
                        val currElement : SectionElement? = item.getValue(SectionElement::class.java)
                        if (currElement != null){
                            currElement.dbKey = item.key.toString()
                            allElements.add(currElement)
                        }
                    }
                    Toast.makeText(context, allElements.size.toString(), Toast.LENGTH_SHORT).show()
                    val upgrade_section_elements_adapter = UpgradeElementAdapter(allElements)
                    upgrade_section_elements_adapter.notifyDataSetChanged()
                    recycler_view.adapter = upgrade_section_elements_adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Couldn't find the database elements", Toast.LENGTH_SHORT).show()
            }

        })
    }
}