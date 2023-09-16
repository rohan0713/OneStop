package com.kotlin.androidtopics.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kotlin.androidtopics.R
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        simpleObserver()
    }

    private fun simpleObserver() {
        val list = listOf("A", "B", "C")
        val observable = Observable.fromIterable(list)

        observable.subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("subs", "On Subscribe is called")
            }

            override fun onError(e: Throwable) {
                Log.d("subs", "On Error is called")
            }

            override fun onComplete() {
                Log.d("subs", "On Complete is called")
            }

            override fun onNext(t: String) {
                Log.d("subs", "On Next is called - $t")
            }

        })
    }
}