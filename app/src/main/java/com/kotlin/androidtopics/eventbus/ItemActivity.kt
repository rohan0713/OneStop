package com.kotlin.androidtopics.eventbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kotlin.androidtopics.R
import com.kotlin.androidtopics.databinding.ActivityItemBinding
import org.greenrobot.eventbus.EventBus

class ItemActivity : AppCompatActivity() {

    lateinit var binding : ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityItemBinding.inflate(layoutInflater).also { binding = it }.root)

        binding.add1.setOnClickListener {
            addItemToCart(it)
        }
    }

    fun addItemToCart(view: View) {
        EventBus.getDefault().post(MessageEvent("new item added"))
    }
}