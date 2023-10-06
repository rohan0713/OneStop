package com.kotlin.androidtopics.eventbus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kotlin.androidtopics.R
import com.kotlin.androidtopics.databinding.ActivityCartBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/*

An event bus in Android is a publish-subscribe pattern that allows different parts of your application to
communicate with each other.
EventBus simplifies the communication between different components for example communications between
different levels of activities or even services.

 */

class CartActivity : AppCompatActivity() {

    lateinit var cartList : ArrayList<MessageEvent>

    lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityCartBinding.inflate(layoutInflater).also { binding = it }.root)
        cartList = ArrayList()

        binding.btnAdd.setOnClickListener {
            Intent(this@CartActivity, ItemActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    // By specifying the ThreadMode when subscribing to an event, you can control
    // whether the event is delivered synchronously or asynchronously.


    // To tell the EventBus to trigger this method we need to add the @Subscribe annotation to the method.
    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun recentUpdate(messageEvent: MessageEvent) {
        cartList.add(messageEvent)
        val count = cartList.size
        binding.itemCount.text = "Total Item Count : $count"
        Toast.makeText(baseContext, "Item added to cart", Toast.LENGTH_LONG).show()
    }

    // We should unregister and re-register the EventBus in the onStart and onDestroy method on the activity.
    override fun onStart() {
        super.onStart()
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}