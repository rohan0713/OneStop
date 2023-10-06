package com.kotlin.androidtopics.pagination.data.remote

import com.kotlin.androidtopics.pagination.names
import retrofit2.Response
import retrofit2.http.GET

interface myApi {

    @GET("names")
    suspend fun getNames() : Response<List<names>>
}