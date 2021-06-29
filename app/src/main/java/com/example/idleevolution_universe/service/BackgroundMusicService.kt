package com.example.idleevolution_universe.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import androidx.annotation.Nullable
import com.example.idleevolution_universe.R

class BackgroundMusicService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    @Nullable
    @Override
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.thememusic)
        mediaPlayer?.isLooping = true; // Set looping
        mediaPlayer?.setVolume(100F, 100F);
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer?.start()
        return super.onStartCommand(intent, flags, startId)
    }
}