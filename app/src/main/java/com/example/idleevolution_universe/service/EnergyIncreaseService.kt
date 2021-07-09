package com.example.idleevolution_universe.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import com.google.firebase.database.FirebaseDatabase
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
            energyHandler.post { println("ez") }
        }
    }
}