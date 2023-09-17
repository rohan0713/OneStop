package com.kotlin.androidtopics.rxjava.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jakewharton.rxbinding4.view.clicks
import com.kotlin.androidtopics.R
import com.kotlin.androidtopics.databinding.ActivityRxjavaBinding
import com.kotlin.androidtopics.rxjava.data.models.ApiResponse
import com.kotlin.androidtopics.rxjava.data.remote.Retrofit
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Predicate
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit

class RxjavaActivity : AppCompatActivity() {

    lateinit var binding: ActivityRxjavaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRxjavaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.clicks()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe{
            networkCall()
        }

        observer()
    }

    private fun observer() {
        val observable = Observable.just("hello") // Created a Observable stream using just
            .flatMap {
                fetch().toObservable()  // flat map the data returned from fetch() into observable stream
            }

        observable.subscribe {
            Log.d("subs", "$it")
        }
    }

    private fun networkCall() {
        val service = Retrofit.api
        val observable = service.getMeals("Indian")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        observable.subscribe(object : Observer<ApiResponse>{
            override fun onSubscribe(d: Disposable) {
                Log.d("subs", "On Subscribe is called")
            }

            override fun onNext(t: ApiResponse) {
                Log.d("subs", "On Next is called - ${t.meals}")
            }

            override fun onError(e: Throwable) {
                Log.d("subs", "On Error is called")
            }

            override fun onComplete() {
                Log.d("subs", "On Complete is called")
            }
        })

    }

    private fun createObservable() {
        val observable = Observable.create<String>{
            it.onNext("a")
            it.onNext("b")
            it.onError(IllegalArgumentException("wrong data"))
            it.onNext("c")
            it.onComplete()
        }

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

    private fun fetch() : Single<List<String>> {
        return Single.fromCallable {
            listOf("alpha", "beta")
        }.subscribeOn(Schedulers.io())
    }
}