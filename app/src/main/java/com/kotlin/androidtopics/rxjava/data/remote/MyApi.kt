package com.kotlin.androidtopics.rxjava.data.remote

import com.kotlin.androidtopics.rxjava.data.models.ApiResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("filter.php")
    fun getMeals(@Query("a") a : String) : Observable<ApiResponse>
}