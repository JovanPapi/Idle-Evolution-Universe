package com.example.idleevolution_universe.ui.upgrade_element

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.idleevolution_universe.R

class UpgradesFragment : Fragment() {

    private lateinit var upgradesViewModel: UpgradesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        upgradesViewModel =
                ViewModelProvider(this).get(UpgradesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_upgrades, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        upgradesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}