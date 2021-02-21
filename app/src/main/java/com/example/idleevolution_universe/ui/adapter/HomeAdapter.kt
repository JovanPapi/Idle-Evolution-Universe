package com.example.idleevolution_universe.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.Section

class HomeAdapter : ListAdapter<Section, HomeAdapter.HomeViewHolder>(SectionComparator()) {
    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentSection: Section? = null
        private val sectionButton: Button = itemView.findViewById(R.id.sectionButton)

        fun setData(section: Section) {

            sectionButton.text = section.name
            sectionButton.setBackgroundResource(section.image)
            currentSection = section
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val listItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.session_item, parent, false)
        return HomeViewHolder(listItemView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val section = getItem(position)
        holder.setData(section)

    }

    class SectionComparator : DiffUtil.ItemCallback<Section>() {
        override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
            return oldItem.id == newItem.id
        }
    }
}