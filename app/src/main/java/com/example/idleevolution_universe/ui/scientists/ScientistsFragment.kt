package com.example.idleevolution_universe.ui.scientists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.idleevolution_universe.R

class ScientistsFragment : Fragment() {
    private lateinit var scientistsViewModel: ScientistsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scientistsViewModel =
            ViewModelProvider(this).get(ScientistsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_scientists, container, false)
        val textView: TextView = root.findViewById(R.id.text_scientists)
        scientistsViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}