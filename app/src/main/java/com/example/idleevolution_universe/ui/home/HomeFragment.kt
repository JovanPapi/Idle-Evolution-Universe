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

        homeAdapter.submitList(SectionData.sections)
        homeAdapter.notifyDataSetChanged()
        recyclerView.adapter = homeAdapter
    }

    interface OpenSectionListener {
        fun openSection(sectionName: String)
    }
}