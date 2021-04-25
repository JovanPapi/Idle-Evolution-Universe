package com.example.idleevolution_universe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.Section
import com.example.idleevolution_universe.entity_model.SectionData
import com.example.idleevolution_universe.ui.adapter.HomeAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    var firebaseDatabase = FirebaseDatabase.getInstance()
    var sectionDatabase = firebaseDatabase.getReference("sections")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.homeRecyclerView)
//        val layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL,true)
//        layoutManager.orientation = GridLayoutManager.VERTICAL

//        val homeViewModel: HomeViewModel by viewModels()

        val homeAdapter = HomeAdapter(object : OpenSectionListener {
            override fun openSection(sectionName: String) {
                //TODO here open the clicked section found by its name
                Toast.makeText(activity, sectionName, Toast.LENGTH_SHORT).show()
            }
        })
//        homeViewModel.sections.observe(viewLifecycleOwner) { sections ->
//            sections.let { homeAdapter.submitList(it) }
//        }
        sectionDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val number: Long = 0
                if (snapshot.childrenCount == number) {
                    SectionData.sections.forEach { section ->
                        sectionDatabase.push().setValue(section)
                    }
                    return
                } else {
                    val sections = mutableListOf<Section>()
                    for (sectionDB: DataSnapshot in snapshot.children) {
                        val section: Section? = sectionDB.getValue(Section::class.java)
                        if (section != null) {
                            section.dbKey = sectionDB.key.toString()
                            sections.add(section)
                        }
                    }
                    homeAdapter.submitList(sections)
                    homeAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    activity,
                    "Sorry, there was some problem fetching the data...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        recyclerView.adapter = homeAdapter


//        val textView: TextView = view.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, {
//            textView.text = it
//        })
    }

    interface OpenSectionListener {
        fun openSection(sectionName: String)
    }
}