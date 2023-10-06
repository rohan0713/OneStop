package com.kotlin.androidtopics.glide.ui.activities

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kotlin.androidtopics.R
import com.kotlin.androidtopics.databinding.ActivityImageBinding
import com.kotlin.androidtopics.glide.application.GetImage
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerActivity

class ImageActivity : AppCompatActivity() {

    lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startActivityForResult(
            UnsplashPickerActivity.getStartingIntent(
                this, // context
                true
            ), 101
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 101) {
            val photos: ArrayList<UnsplashPhoto>? = data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)
            // use your photos here
            val list = photos?.get(0)
            Log.d("unsplash", list.toString())
        }
    }
}