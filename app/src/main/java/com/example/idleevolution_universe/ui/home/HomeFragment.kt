package com.example.idleevolution_universe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.database.UniverseDatabase
import com.example.idleevolution_universe.entity_model.DatabaseInstance
import com.example.idleevolution_universe.ui.adapter.HomeAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.homeRecyclerView)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        val homeViewModel: HomeViewModel by viewModels {
            HomeViewModelFactory((activity?.application as DatabaseInstance).database)
        }

        val homeAdapter = HomeAdapter()
        homeViewModel.sections.observe(viewLifecycleOwner) { sections ->
            sections.let { homeAdapter.submitList(it) }
        }
        recyclerView.adapter = homeAdapter
        recyclerView.layoutManager = layoutManager


//        val textView: TextView = view.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, {
//            textView.text = it
//        })
    }
}