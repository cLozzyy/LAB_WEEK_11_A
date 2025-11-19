package com.example.lab_week_11_a

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class PreferenceApplication : Application() {

    // Nama file SharedPreferences
    private val PREFS_NAME = "MyPrefsFile"

    // Instance SharedPreferences
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        // Inisialisasi SharedPreferences saat aplikasi dibuat
        sharedPreferences = applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}