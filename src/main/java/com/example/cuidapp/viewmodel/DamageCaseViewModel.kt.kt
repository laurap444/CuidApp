package com.example.cuidapp.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cuidapp.data.DamageCase

class DamageCaseViewModel(private val repository: DamageCaseRepository) : ViewModel() {

    private val _cases = MutableLiveData<List<DamageCase>>()
    val cases: LiveData<List<DamageCase>> = _cases



    fun loadCases() {
        viewModelScope.launch {
            _cases.postValue(repository.getOpenCases())
        }
    }

    fun addCase(damageCase: DamageCase) {
        viewModelScope.launch {
            repository.insert(damageCase)
            loadCases()
        }
    }

    fun addDamageCase(title: String, description: String, s: String) {

    }
}