package com.example.idleevolution_universe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.SectionData
import com.example.idleevolution_universe.ui.adapter.HomeAdapter
import com.google.firebase.database.FirebaseDatabase

class HomeFragment : Fragment() {

    val sectionRef = FirebaseDatabase.getInstance().reference.child("sections")

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
                val bundle = Bundle()
                bundle.putString("sectionName", sectionName)
                findNavController().navigate(
                    R.id.action_navigation_home_to_showSectionElementsFragment,
                    bundle
                )
            }
        })

//        homeViewModel.sections.observe(viewLifecycleOwner) { sections ->
//            sections.let { homeAdapter.submitList(it) }
//        }
//        FirebaseDatabase.getInstance().reference.child("sections").removeValue()
//        sectionRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.hasChildren()) {
//                    for (sectionDB: DataSnapshot in snapshot.children) {
//                        val section: Section? = sectionDB.getValue(Section::class.java)
//                        if (section != null) {
//                            section.dbKey = sectionDB.key.toString()
//                            RealTimeSectionData.sections.add(section)
//                        }
//                    }
//                    homeAdapter.submitList(RealTimeSectionData.sections)
//                    homeAdapter.notifyDataSetChanged()
//                } else {
//                    SectionData.sections.forEach { sectionRef.push().setValue(it) }
//                    homeAdapter.submitList(SectionData.sections)
//                    homeAdapter.notifyDataSetChanged()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(
//                    activity,
//                    "Sorry, there was some problem fetching the data...",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })
        homeAdapter.submitList(SectionData.sections)
        homeAdapter.notifyDataSetChanged()
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