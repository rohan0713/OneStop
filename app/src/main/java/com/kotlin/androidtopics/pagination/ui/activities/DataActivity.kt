package com.kotlin.androidtopics.pagination.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.androidtopics.R
import com.kotlin.androidtopics.databinding.ActivityDataBinding
import com.kotlin.androidtopics.pagination.data.remote.RetrofitClient
import com.kotlin.androidtopics.pagination.names
import com.kotlin.androidtopics.pagination.ui.adapters.DataAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataActivity : AppCompatActivity() {

    lateinit var binding: ActivityDataBinding
    lateinit var dataAdapter : DataAdapter

    private var isLoading = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadMoreData()


        binding.rvData.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                Log.d("vCount", visibleItemCount.toString())
                Log.d("vCount", totalItemCount.toString())
                Log.d("vCount", firstVisibleItemPosition.toString())

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                        // Load more data here
                        loadMoreData()
                    }
                }
            }
        })
    }

    private fun loadMoreData() {

        binding.progressBar.visibility = View.VISIBLE
        isLoading = true

        lifecycleScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.api.getNames()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    val list = response.body()!!
                    dataAdapter = DataAdapter(list)
                    binding.rvData.adapter = dataAdapter
                    binding.progressBar.visibility = View.GONE
                    isLastPage = true
                }
            }
        }
    }

    private fun setupRecyclerView() {
        dataAdapter = DataAdapter(listOf())
        binding.rvData.apply {
            adapter = dataAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}