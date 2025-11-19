// File: PreferenceWrapper.kt
package com.example.lab_week_11_a.viewmodels

import android.content.Context
import android.content.SharedPreferences

class PreferenceWrapper(context: Context) {

    private val PREFS_NAME = "com.example.lab_week_11_a.PREFERENCES"
    private val PREF_KEY_TEXT = "pref_text"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /**
     * Mengambil teks dari SharedPreferences.
     * Mengembalikan string kosong jika tidak ada data.
     */
    fun getText(): String {
        return sharedPref.getString(PREF_KEY_TEXT, "") ?: ""
    }

    /**
     * Menyimpan teks ke SharedPreferences.
     */
    fun setText(newText: String) {
        val editor = sharedPref.edit()
        editor.putString(PREF_KEY_TEXT, newText)
        editor.apply() // .apply() untuk menyimpan secara asynchronous
    }
}
