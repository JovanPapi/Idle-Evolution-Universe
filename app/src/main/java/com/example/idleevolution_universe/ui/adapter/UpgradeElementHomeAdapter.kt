package com.example.idleevolution_universe.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.Section
import com.example.idleevolution_universe.entity_model.SectionData
import com.example.idleevolution_universe.ui.upgrade_element.UpgradesFragment

// Passed parameter which is object of 'UpgradesFragment' and references to the interface ->
// -> 'OpenUpgradeSectionListener' in the fragment.
class UpgradeElementHomeAdapter(private var sections: MutableList<Section>,
                                private val obj_listener: UpgradesFragment.OpenUpgradeSectionListener) : RecyclerView.Adapter<UpgradeElementHomeAdapter.UpgradeElementHomeViewHolder>(){

    inner class UpgradeElementHomeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        private var section_btn: Button = itemview.findViewById(R.id.fragment_upgrades_button)

        fun setData(section: Section) {
            section_btn.text = section.name
        }

        fun itemClickListener(section: Section){
            // If item(each item is button) is clicked start onClick event and with the object ->
            // -> call the function in the interface.
            section_btn.setOnClickListener { obj_listener.openUpgradeSection(section) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpgradeElementHomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_upgrades_home, parent, false)
        return UpgradeElementHomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpgradeElementHomeViewHolder, position: Int) {
        val section = this.sections[position]

        holder.setData(section)
        // Check if the item is clicked
        holder.itemClickListener(section)
        }

    override fun getItemCount(): Int {
        return this.sections.size
    }

}