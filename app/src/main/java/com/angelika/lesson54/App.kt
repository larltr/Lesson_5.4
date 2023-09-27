package com.angelika.lesson54

import android.app.Application
import com.angelika.lesson54.data.remote.RetrofitClient

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var retrofitClient: RetrofitClient
            private set
    }

    override fun onCreate() {
        super.onCreate()
        retrofitClient = RetrofitClient()
    }
}