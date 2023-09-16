package com.kotlin.androidtopics.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kotlin.androidtopics.R
import com.kotlin.androidtopics.databinding.ActivityMainBinding
import com.kotlin.androidtopics.rxjava.ui.activities.RxjavaActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            Intent(this@MainActivity, RxjavaActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}