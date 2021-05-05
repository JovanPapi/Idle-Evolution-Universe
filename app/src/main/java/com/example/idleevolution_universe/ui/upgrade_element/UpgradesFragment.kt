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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.SectionData
import com.example.idleevolution_universe.ui.adapter.UpgradeElementAdapter

class UpgradesFragment : Fragment() {

//    private lateinit var upgradesViewModel: UpgradesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {

        val root = inflater.inflate(R.layout.fragment_upgrades, container, false)

//        upgradesViewModel = ViewModelProvider(this).get(UpgradesViewModel::class.java)
//        upgradesViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler_view: RecyclerView = view.findViewById(R.id.upgrades_recyclerView)

        val upgrade_element_adapter = UpgradeElementAdapter(context, SectionData.sections)
        upgrade_element_adapter.notifyDataSetChanged()
        recycler_view.adapter = upgrade_element_adapter
    }
}