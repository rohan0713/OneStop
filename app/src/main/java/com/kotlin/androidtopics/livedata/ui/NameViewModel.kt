package com.kotlin.androidtopics.livedata.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.androidtopics.livedata.data.models.Names
import kotlinx.coroutines.launch
import retrofit2.Response

class NameViewModel(
val repository: NameRepository
) : ViewModel() {

    val names : MutableLiveData<List<Names>> = MutableLiveData()

    init {
        getNames()
    }

    fun getNames() = viewModelScope.launch {
        val response = repository.getNames()
        names.postValue(response.body())
    }

    private fun handleResponse(response: Response<List<Names>>): List<Names>? {

        if(response.isSuccessful){
            response.body()?.let {
               result -> return result
            }
        }
        return listOf()
    }


}