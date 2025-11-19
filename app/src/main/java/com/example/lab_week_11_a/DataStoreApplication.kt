package com.example.lab_week_11_a

import android.app.Application
import com.example.lab_week_11_a.wrappers.DataStoreWrapper

class DataStoreApplication : Application() {

    // Instance DataStoreWrapper
    lateinit var dataStoreWrapper: DataStoreWrapper

    override fun onCreate() {
        super.onCreate()
        // Inisialisasi DataStoreWrapper dengan context aplikasi
        dataStoreWrapper = DataStoreWrapper(this)
    }
}