package com.example.idleevolution_universe.ui.buildElements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BuildElementViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is buildElements Fragment"
    }
    val text: LiveData<String> = _text
}