package com.example.idleevolution_universe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.idleevolution_universe.entity_model.SectionElement
import com.example.idleevolution_universe.entity_model.SectionElements
import com.example.idleevolution_universe.service.BackgroundMusicService
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private val quantumRef = FirebaseDatabase.getInstance().reference.child("quantum")
    private val nanoRef = FirebaseDatabase.getInstance().reference.child("nano")
    private val complexRef = FirebaseDatabase.getInstance().reference.child("complex")
    private val energyProductionRef =
        FirebaseDatabase.getInstance().reference.child("energyProduction")
    private var btnMusicChange: Button? = null
    private var musicOnOff: Boolean = true
    private var backgroundMusicIntent: Intent? = null

//    private var counter = 0
//    private var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        btnMusicChange = findViewById(R.id.btnMusic)

        createQuantumDB()
        createNanoDB()
        createComplexDB()
        createEnergyProductionDB()

        val navController = findNavController(R.id.nav_host_fragment)
        supportActionBar?.hide()
        navView.setupWithNavController(navController)

        backgroundMusicIntent = Intent(applicationContext, BackgroundMusicService::class.java)
//        startService(backgroundMusicIntent)

        btnMusicChange?.setOnClickListener {
            if (musicOnOff) {
                musicOnOff = false
                stopService(backgroundMusicIntent)
                btnMusicChange?.setBackgroundResource(R.drawable.ic_music_off)
            } else {
                musicOnOff = true
                startService(backgroundMusicIntent)
                btnMusicChange?.setBackgroundResource(R.drawable.ic_music_on)
            }
        }
//        handler.post(object : Runnable {
//            override fun run() {
//                counter++
//                println(counter)
//                handler.postDelayed(this, 1000)
//            }
//        })
    }

    private fun createEnergyProductionDB() {
        energyProductionRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    energyProductionRef.push().setValue(0)
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

    override fun onPause() {
        super.onPause()
//        stopService(backgroundMusicIntent)
    }

    override fun onResume() {
        super.onResume()
//        startService(backgroundMusicIntent)
//        startProductionIncrement()
    }
}