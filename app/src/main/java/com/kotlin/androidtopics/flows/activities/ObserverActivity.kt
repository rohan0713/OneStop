package com.kotlin.androidtopics.flows.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.kotlin.androidtopics.R
import com.kotlin.androidtopics.databinding.ActivityObserveBinding
import com.kotlin.androidtopics.databinding.ActivityObserverBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ObserverActivity : AppCompatActivity() {

    lateinit var binding: ActivityObserverBinding
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityObserverBinding.inflate(layoutInflater).also { binding = it }.root)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        binding.btnLiveData.setOnClickListener {
            viewModel.triggerLiveData()
        }

        binding.btnStateFlow.setOnClickListener {
            viewModel.triggerStateFlow()
        }

        binding.btnSharedFlow.setOnClickListener {
            viewModel.triggerSharedFlow()
        }

        observe()
    }

    private fun observe() {

        viewModel.liveData.observe(this) {
            binding.tvLiveData.text = it
        }

        lifecycleScope.launch {
            viewModel.stateFlow.collectLatest {
                binding.StateFlow.text = it
            }
        }

        lifecycleScope.launch {
            viewModel.sharedFlow.collectLatest{
//                binding.SharedFlow.text = it
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

    }
}