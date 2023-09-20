package com.kotlin.androidtopics.livedata.ui

import com.kotlin.androidtopics.livedata.network.RetrofitClient

class NameRepository {

    suspend fun getNames() = RetrofitClient.api.getNames()

}