package com.example.idleevolution_universe.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.Section
import com.example.idleevolution_universe.entity_model.SectionData

class UpgradeElementHomeAdapter(private var sections_obj: MutableList<Section>) : RecyclerView.Adapter<UpgradeElementHomeAdapter.UpgradeElementHomeViewHolder>(){

    private var counter = 0

    inner class UpgradeElementHomeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        private var section_btn: Button = itemview.findViewById(R.id.fragment_upgrades_button)

        fun setData(section: Section) {
            section_btn.text = section.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpgradeElementHomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_upgrades_home, parent, false)
        return UpgradeElementHomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpgradeElementHomeViewHolder, position: Int) {
        val section = this.sections_obj[position]

        holder.setData(section)
        }

    override fun getItemCount(): Int {
        return this.sections_obj.size
    }

}