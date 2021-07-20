package com.example.idleevolution_universe.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.TextView
import android.widget.Toast
import com.example.idleevolution_universe.MainActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class EnergyIncreaseService : Service() {
    private val energyProductionRef =
        FirebaseDatabase.getInstance().reference.child("energyProduction")
    private val energyHandler = Handler()
    private var energyTimer: Timer? = null
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        if (energyTimer != null) {
            energyTimer!!.cancel()
        } else {
            energyTimer = Timer()
        }
        energyTimer!!.scheduleAtFixedRate(TimeDisplay(), 0, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        energyTimer!!.cancel()
    }

    inner class TimeDisplay : TimerTask() {
        override fun run() {
            energyHandler.post {
                var tempEnergy = Integer.parseInt(MainActivity.tvUserCurrentEnergy?.text.toString())
                tempEnergy += MainActivity.userCurrentEnergyProduction
                MainActivity.tvUserCurrentEnergy?.text = tempEnergy.toString()
            }
        }
    }
}