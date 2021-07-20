package com.example.idleevolution_universe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.idleevolution_universe.entity_model.SectionElement
import com.example.idleevolution_universe.entity_model.SectionElements
import com.example.idleevolution_universe.service.BackgroundMusicService
import com.example.idleevolution_universe.service.EnergyIncreaseService
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
    private val userTotalEnergyRef =
        FirebaseDatabase.getInstance().reference.child("userTotalEnergy")
    private var btnMusicChange: Button? = null
    private var musicOnOff: Boolean = true
    private var backgroundMusicService = Intent()
    private var energyIncreaseService = Intent()

    companion object {
        var userCurrentEnergyProduction: Int = 0
        var tvUserCurrentEnergy: TextView? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        btnMusicChange = findViewById(R.id.btnMusic)
        tvUserCurrentEnergy = findViewById(R.id.tvUserTotalEnergy)

        createQuantumDB()
        createNanoDB()
        createComplexDB()
        createEnergyProductionDB()

        val navController = findNavController(R.id.nav_host_fragment)
        supportActionBar?.hide()
        navView.setupWithNavController(navController)

        backgroundMusicService = Intent(applicationContext, BackgroundMusicService::class.java)
        startService(backgroundMusicService)
        energyIncreaseService = Intent(applicationContext, EnergyIncreaseService::class.java)
        startService(energyIncreaseService)


        btnMusicChange?.setOnClickListener {
            if (musicOnOff) {
                musicOnOff = false
                stopService(backgroundMusicService)
                btnMusicChange?.setBackgroundResource(R.drawable.ic_music_off)
            } else {
                musicOnOff = true
                startService(backgroundMusicService)
                btnMusicChange?.setBackgroundResource(R.drawable.ic_music_on)
            }
        }
    }

    private fun createEnergyProductionDB() {
        energyProductionRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    energyProductionRef.setValue(0)
                } else {
                    userCurrentEnergyProduction = Integer.parseInt(snapshot.value.toString())
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
        userTotalEnergyRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    userTotalEnergyRef.setValue(15)
                } else {
                    tvUserCurrentEnergy?.text = snapshot.value.toString()
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
        val totalEnergyProduced = Integer.parseInt(tvUserCurrentEnergy?.text.toString())
        userTotalEnergyRef.setValue(totalEnergyProduced)
        stopService(backgroundMusicService)
        stopService(energyIncreaseService)
    }

    override fun onResume() {
        super.onResume()
        if (musicOnOff) {
            startService(backgroundMusicService)
        }
        startService(energyIncreaseService)
    }

    override fun onDestroy() {
        super.onDestroy()
        val totalEnergyProduced = Integer.parseInt(tvUserCurrentEnergy?.text.toString())
        userTotalEnergyRef.setValue(totalEnergyProduced)
        stopService(backgroundMusicService)
        stopService(energyIncreaseService)
    }
}