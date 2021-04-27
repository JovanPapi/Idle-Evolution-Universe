package com.example.idleevolution_universe

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.idleevolution_universe.entity_model.Section
import com.example.idleevolution_universe.entity_model.SectionElement
import com.example.idleevolution_universe.entity_model.SectionElements
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private val quantumRef = FirebaseDatabase.getInstance().reference.child("quantum")
    private val nanoRef = FirebaseDatabase.getInstance().reference.child("nano")
    private val complexRef = FirebaseDatabase.getInstance().reference.child("complex")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        createQuantumDB()
        createNanoDB()
        createComplexDB()

        val navController = findNavController(R.id.nav_host_fragment)
        supportActionBar?.hide()
        navView.setupWithNavController(navController)
    }

    private fun createQuantumDB() {
        val quantumElements: List<SectionElement> = SectionElements.sectionElements[0]
        quantumRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.hasChildren()) {
                    quantumElements.forEach { quantumRef.push().setValue(it) }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    applicationContext,
                    "Sorry, something went wrong when saving the initial data",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun createNanoDB() {
        val nanoElements: List<SectionElement> = SectionElements.sectionElements[1]
        nanoRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.hasChildren()) {
                    nanoElements.forEach { nanoRef.push().setValue(it) }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    applicationContext,
                    "Sorry, something went wrong when saving the initial data",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun createComplexDB() {
        val complexElements: List<SectionElement> = SectionElements.sectionElements[2]
        complexRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.hasChildren()) {
                    complexElements.forEach { complexRef.push().setValue(it) }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    applicationContext,
                    "Sorry, something went wrong when saving the initial data",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }
}