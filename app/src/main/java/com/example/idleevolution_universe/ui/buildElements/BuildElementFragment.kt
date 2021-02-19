package com.example.idleevolution_universe.ui.buildElements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.idleevolution_universe.R

class BuildElementFragment : Fragment() {

    private lateinit var buildElementViewModel: BuildElementViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        buildElementViewModel =
                ViewModelProvider(this).get(BuildElementViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_build_elements, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        buildElementViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}