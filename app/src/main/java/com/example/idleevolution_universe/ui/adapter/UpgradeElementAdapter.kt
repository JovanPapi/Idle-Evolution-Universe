package com.example.idleevolution_universe.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.SectionElement
import com.example.idleevolution_universe.ui.upgrade_element.UpgradesFragment

// If we use 'ListAdapter<>()' instead of 'RecyclerView.Adapter<>()' we can pass the list with "submitList()" ->
// -> without the need to have parameter in the class
class UpgradeElementAdapter(private val event_listener: UpgradesFragment.ElementClickedListenerInterface) :
    ListAdapter<SectionElement, UpgradeElementAdapter.UpgradeElementViewHolder>(ElementsComparator()) {

    inner class UpgradeElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val elementNameUpgrade: TextView =
            itemView.findViewById(R.id.elementNameUpgrade)
        private val elementImageUpgrade: ImageView =
            itemView.findViewById(R.id.elementImageUpgrade)
        private val upgrade_element_image_layout: LinearLayout =
            itemView.findViewById(R.id.elements_view)


        fun setData(element: SectionElement) {
            elementNameUpgrade.text = element.name + ":" + element.requiredElementQuantity
            elementImageUpgrade.setImageResource(element.image)
        }

        fun checkIfElementIsClicked(currentElement: SectionElement) {
            upgrade_element_image_layout.setOnClickListener {
                event_listener.upgradeClickedElement(
                    currentElement
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpgradeElementViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.upgrades_item_view, parent, false)
        return UpgradeElementViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpgradeElementViewHolder, position: Int) {
        val element = getItem(position)
        holder.setData(element)
        holder.checkIfElementIsClicked(element)
    }

// Don't need this fun with ListAdapter
//    override fun getItemCount(): Int {
//        return this.section_elements.size
//    }


    class ElementsComparator() : DiffUtil.ItemCallback<SectionElement>() {
        override fun areItemsTheSame(oldItem: SectionElement, newItem: SectionElement): Boolean {
            // Return true or false only if objects are from same type
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SectionElement, newItem: SectionElement): Boolean {
            return oldItem.name == newItem.name
        }

    }
}