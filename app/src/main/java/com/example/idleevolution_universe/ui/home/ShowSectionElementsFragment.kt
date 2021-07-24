package com.example.idleevolution_universe.ui.home

import android.R.attr.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.SectionElement
import com.example.idleevolution_universe.ui.adapter.SectionElementsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*


class ShowSectionElementsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_section_elements, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sectionDbKey = arguments?.getString("sectionName")?.toLowerCase(Locale.ROOT)
        val sectionRef = FirebaseDatabase.getInstance().reference.child(sectionDbKey!!)
        val btnBack: ImageButton = view.findViewById(R.id.sectionButtonBack)

        val recyclerView: RecyclerView = view.findViewById(R.id.sectionElementsRecyclerView)
        val sectionElementsAdapter = SectionElementsAdapter(object : OpenElementListener {
            override fun openElement(elementDbKey: String, sectionElement: String) {
                val bundle = Bundle()
                bundle.putString("elementDbKey", elementDbKey)
                bundle.putString("sectionElement", sectionElement)
                bundle.putString("sectionName", sectionDbKey)
                findNavController().navigate(R.id.action_showSectionElementsFragment_to_elementPopUpActivity, bundle)
            }
        })
        sectionRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChildren()) {
                    val elements = mutableListOf<SectionElement>()
                    for (item: DataSnapshot in snapshot.children) {
                        val sectionElement: SectionElement? =
                            item.getValue(SectionElement::class.java)
                        if (sectionElement != null) {
                            sectionElement.dbKey = item.key.toString()
                            elements.add(sectionElement)
                        }
                    }
                    sectionElementsAdapter.submitList(elements)
                    sectionElementsAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = sectionElementsAdapter

        btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_showSectionElementsFragment_to_navigation_home)
        }

    }

    interface OpenElementListener {
        fun openElement(elementDbKey: String, sectionElement: String)
    }
}