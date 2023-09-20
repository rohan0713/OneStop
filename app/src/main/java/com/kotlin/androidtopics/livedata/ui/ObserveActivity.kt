package com.kotlin.androidtopics.livedata.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.androidtopics.databinding.ActivityObserveBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ObserveActivity : AppCompatActivity() {

    lateinit var binding: ActivityObserveBinding
    lateinit var nameAdapter: NameAdapter
    lateinit var viewModel: NameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityObserveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerview()

        val repository = NameRepository()
        val Provider = ViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this@ObserveActivity, Provider)[NameViewModel::class.java]

        viewModel.names.observe(this, Observer {
            response ->
            nameAdapter.updateList(response)
            binding.rvNames.adapter = nameAdapter
        })

        lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                delay(5000)
                viewModel.getNames()
            }
        }
    }

    private fun setupRecyclerview() {
        nameAdapter = NameAdapter(listOf())
        binding.rvNames.apply {
            adapter = nameAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}