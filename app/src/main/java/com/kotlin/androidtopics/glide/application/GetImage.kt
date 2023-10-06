package com.kotlin.androidtopics.glide.application

import android.app.Application
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker

class GetImage : Application() {

    override fun onCreate() {
        super.onCreate()

        UnsplashPhotoPicker.init(
            this,
            "u2pEnbOgc7anl_KnBWT7WsdECfBhDg7X5H9MAKXMcgE",
            "EwdKqqefFSO3mQoXUp2L5yAXYFQ_yhs1W9yGeXBuWHw"
        )
    }
}