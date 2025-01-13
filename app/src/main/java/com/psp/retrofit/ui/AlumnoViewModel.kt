package com.psp.retrofit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlumnoViewModel : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun viewCreated(nombre: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(UiState(nombre = nombre))

        }
    }

    data class UiState(
        val nombre: String = "",
    )

}