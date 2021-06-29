package com.example.idleevolution_universe.ui.upgrade_element

import android.app.Activity
import android.app.Application
import android.content.ContentProvider
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.SectionData
import com.example.idleevolution_universe.entity_model.SectionElements
import com.example.idleevolution_universe.ui.adapter.UpgradeElementAdapter
import com.example.idleevolution_universe.ui.adapter.UpgradeElementHomeAdapter

class UpgradesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {

        val root = inflater.inflate(R.layout.fragment_upgrades_home_view, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler_view: RecyclerView = view.findViewById(R.id.fragment_upgrades_home_recyclerView)

        // Take only the visible sections, because the adapter creates button for every section ->
        // -> even though those sections are hidden. It lists all items.
        val result_list_section = SectionData.sections.subList(0, 3)

        val upgrade_section_adapter = UpgradeElementHomeAdapter(result_list_section)
        upgrade_section_adapter.notifyDataSetChanged()
        recycler_view.adapter = upgrade_section_adapter
    }
}