package com.example.idleevolution_universe.ui.upgradeElements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UpgradesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is upgradeElements Fragment"
    }
    val text: LiveData<String> = _text
}