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
import com.example.idleevolution_universe.ui.home.HomeFragment
import com.example.idleevolution_universe.ui.home.ShowSectionElementsFragment

class SectionElementsAdapter(private val listener: ShowSectionElementsFragment.OpenElementListener) :
    ListAdapter<SectionElement, SectionElementsAdapter.SectionElementsViewHolder>(SectionComparator()) {

    // ViewHolder gets and sets the itemView of each item in the RecyclerView
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
            elementProduction.text =
                "Energy: " + sectionElement.energyProductionPerSecond.toString() + "/s"
            elementImage.setImageResource(sectionElement.image)
        }

        fun onElementClick(elementDbKey: String, sectionElement: String) {
            elementImage.setOnClickListener { listener.openElement(elementDbKey,sectionElement) }
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
        holder.onElementClick(sectionElement.dbKey, sectionElement.section)
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