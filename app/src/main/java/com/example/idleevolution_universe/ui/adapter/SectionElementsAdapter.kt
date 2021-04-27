package com.example.idleevolution_universe.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.SectionElement

class SectionElementsAdapter() :
    ListAdapter<SectionElement, SectionElementsAdapter.SectionElementsViewHolder>(SectionComparator()) {
    inner class SectionElementsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val elementName: TextView = itemView.findViewById(R.id.nameOfElement)
        private val elementProductionPow: TextView =
            itemView.findViewById(R.id.productionPowElement)
        private val elementProduction: TextView =
            itemView.findViewById(R.id.totalElementEnergyProduction)
        private val elementImage: ImageView = itemView.findViewById(R.id.imageOfElement)

        fun setData(sectionElement: SectionElement) {
            elementName.text = "Name: " + sectionElement.name
            elementProductionPow.text = "Updates: x" + sectionElement.productionPow.toString()
            elementProduction.text = "Energy: " + sectionElement.energyProductionPerSecond.toString() + "/s"
            elementImage.setImageResource(sectionElement.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionElementsViewHolder {
        val listItemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.section_element_item, parent, false)
        return SectionElementsViewHolder(listItemView)
    }

    override fun onBindViewHolder(holder: SectionElementsViewHolder, position: Int) {
        val sectionElement = getItem(position)
        holder.setData(sectionElement)
    }

    class SectionComparator : DiffUtil.ItemCallback<SectionElement>() {
        override fun areItemsTheSame(oldItem: SectionElement, newItem: SectionElement): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SectionElement, newItem: SectionElement): Boolean {
            return oldItem.name == newItem.name
        }
    }
}