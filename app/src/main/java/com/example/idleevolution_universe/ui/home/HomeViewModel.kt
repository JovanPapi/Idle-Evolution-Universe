package com.example.idleevolution_universe.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.idleevolution_universe.database.UniverseDatabase
import com.example.idleevolution_universe.entity_model.Section

class HomeViewModel(private val universeDatabase: UniverseDatabase) : ViewModel() {

    val sections: LiveData<List<Section>> = universeDatabase.sectionDao().getAll().asLiveData()

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text
}
class HomeViewModelFactory(private val repository: UniverseDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}