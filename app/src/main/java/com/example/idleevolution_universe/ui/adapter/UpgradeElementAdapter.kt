package com.example.idleevolution_universe.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.Section
import com.example.idleevolution_universe.entity_model.SectionElement

class UpgradeElementAdapter(val context: Context?, private val elements: MutableList<Section>): RecyclerView.Adapter<UpgradeElementAdapter.UpgradeElementViewHolder>(){

    inner class UpgradeElementViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val element_name_required: TextView = itemView.findViewById(R.id.element_name_required_for_upgrade)
        private val element_image_required: ImageView = itemView.findViewById(R.id.element_image_required_for_upgrade)
        private val element_name_upgrade: TextView = itemView.findViewById(R.id.element_name_for_upgrade)
        private val element_image_upgrade: ImageView = itemView.findViewById(R.id.element_image_for_upgrade)

        private val counter = 0

        fun setData(element: Section){
            element_name_required.text = element.name + ": " + counter
            element_image_required.setImageResource(element.image)
            element_name_upgrade.text = element.name + ": x5 income"
            element_image_upgrade.setImageResource(element.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpgradeElementViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.upgrades_item_view, parent, false)
        return UpgradeElementViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpgradeElementViewHolder, position: Int) {
        val element = this.elements[position]
        holder.setData(element)
    }

    override fun getItemCount(): Int {
        return this.elements.size
    }
}