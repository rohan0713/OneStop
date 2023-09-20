package com.kotlin.androidtopics.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.androidtopics.databinding.ActivityMainBinding
import com.kotlin.androidtopics.location.LocationActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            Intent(this@MainActivity, LocationActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}