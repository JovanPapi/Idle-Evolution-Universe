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
import com.example.idleevolution_universe.entity_model.Section
import com.example.idleevolution_universe.entity_model.SectionElement
import com.example.idleevolution_universe.ui.upgrade_element.ShowUpgradeSectionElementsFragment

// If we use 'ListAdapter<>()' instead of 'RecyclerView.Adapter<>()' we can pass the list with "submitList()" ->
// -> without the need to have parameter in the class
class UpgradeElementAdapter(private val event_listener: ShowUpgradeSectionElementsFragment.ElementClickedListenerInterface) :
    ListAdapter<SectionElement, UpgradeElementAdapter.UpgradeElementViewHolder>(ElementsComparator()){

    inner class UpgradeElementViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val element_name_required: TextView = itemView.findViewById(R.id.element_name_required_for_upgrade)
        private val element_image_required: ImageView = itemView.findViewById(R.id.element_image_required_for_upgrade)
        private val element_name_upgrade: TextView = itemView.findViewById(R.id.element_name_for_upgrade)
        private val element_image_upgrade: ImageView = itemView.findViewById(R.id.element_image_for_upgrade)

        private val counter = 0

        fun setData(element: SectionElement) {
                element_name_required.text = element.name + ": " + counter
                element_image_required.setImageResource(element.image)
                element_name_upgrade.text = element.name + ": x5 income"
                element_image_upgrade.setImageResource(element.image)
        }

        fun checkIfElementIsClicked(element_dbKey: String, element_section: String){
            element_image_required.setOnClickListener { event_listener.openClickedElement(element_dbKey, element_section) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpgradeElementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.upgrades_item_view, parent, false)
        return UpgradeElementViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpgradeElementViewHolder, position: Int) {
        val element = getItem(position)
        holder.setData(element)
        holder.checkIfElementIsClicked(element.dbKey, element.section)
    }

// Don't need this fun with ListAdapter
//    override fun getItemCount(): Int {
//        return this.section_elements.size
//    }



    class ElementsComparator() : DiffUtil.ItemCallback<SectionElement>(){
        override fun areItemsTheSame(oldItem: SectionElement, newItem: SectionElement): Boolean {
            // Return true or false only if objects are from same type
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SectionElement, newItem: SectionElement): Boolean {
            return oldItem.name == newItem.name
        }

    }
}