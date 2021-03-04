package com.alex.quickapp.base

import android.app.Application

class ScanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object {

        private lateinit var mInstance: ScanApplication

        fun getInstance(): ScanApplication {
            return mInstance
        }
    }
}