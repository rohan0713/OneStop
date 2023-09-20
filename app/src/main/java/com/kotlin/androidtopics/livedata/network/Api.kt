package com.kotlin.androidtopics.livedata.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.androidtopics.livedata.data.models.Names
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("names")
    suspend fun getNames() : Response<List<Names>>
}