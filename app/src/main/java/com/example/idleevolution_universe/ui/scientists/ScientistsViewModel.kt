package com.example.idleevolution_universe.ui.scientists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScientistsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Scientists Fragment"
    }
    val text: LiveData<String> = _text
}