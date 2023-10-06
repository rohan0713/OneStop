package com.kotlin.androidtopics.flows.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    private val _liveData = MutableLiveData("Hello world")
    val liveData : MutableLiveData<String> = _liveData

    private val _stateFlow = MutableStateFlow("Hello world")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun triggerLiveData(){
        _liveData.value = "livedata"
    }

    fun triggerStateFlow(){
        _stateFlow.value = "Stateflow"
    }

   fun triggerSharedFlow(){
        viewModelScope.launch {
            _sharedFlow.emit("Shared flow")
        }
    }
}