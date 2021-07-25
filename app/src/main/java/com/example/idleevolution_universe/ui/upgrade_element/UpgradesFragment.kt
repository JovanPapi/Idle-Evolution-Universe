package com.example.idleevolution_universe.ui.upgrade_element

import android.app.Activity
import android.app.Application
import android.content.ContentProvider
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.Section
import com.example.idleevolution_universe.entity_model.SectionData
import com.example.idleevolution_universe.entity_model.SectionElements
import com.example.idleevolution_universe.ui.adapter.UpgradeElementAdapter
import com.example.idleevolution_universe.ui.adapter.UpgradeElementHomeAdapter
import com.example.idleevolution_universe.ui.home.HomeFragment

class UpgradesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {

        val root = inflater.inflate(R.layout.fragment_upgrades_home_view, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler_view: RecyclerView =
            view.findViewById(R.id.fragment_upgrades_home_recyclerView)


        // Take only the visible sections, because the adapter creates button for every section ->
        // -> even though those sections are hidden. It lists all items.
        val result_list_section = SectionData.sections.subList(0, 3)

        // Send an object to the adapter that reference to the interface below, and override ->
        // -> the function to access the next fragment
        val upgrade_section_adapter =
            UpgradeElementHomeAdapter(result_list_section, object : OpenUpgradeSectionListener {
                override fun openUpgradeSection(curr_section: Section) {
                    val bundle = Bundle()
                    bundle.putString("section_name", curr_section.name)
                    findNavController().navigate(
                        R.id.action_navigation_upgrades_to_showUpgradeSectionElementsFragment,
                        bundle
                    )
                }
            })

        upgrade_section_adapter.notifyDataSetChanged()
        recycler_view.adapter = upgrade_section_adapter


    }

    // Bridge between the fragment and adapter
    interface OpenUpgradeSectionListener {
        fun openUpgradeSection(curr_section: Section)
    }
}