// File: PreferenceViewModel.kt
package com.example.lab_week_11_a.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PreferenceViewModel(private val wrapper: PreferenceWrapper) : ViewModel() {

    // MutableLiveData: Dapat diubah nilainya
    private val _textLiveData = MutableLiveData<String>()

    // LiveData: Diekspos ke Activity/Fragment, hanya bisa dibaca (di-observe)
    val textLiveData: LiveData<String> = _textLiveData

    init {
        // Muat nilai yang tersimpan dari Wrapper saat ViewModel dibuat
        _textLiveData.value = wrapper.getText()
    }

    /**
     * Menyimpan teks baru menggunakan Wrapper dan memperbarui LiveData.
     */
    fun saveText(newText: String) {
        // 1. Simpan ke SharedPreferences melalui Wrapper
        wrapper.setText(newText)

        // 2. Perbarui LiveData agar UI langsung ter-update
        _textLiveData.value = newText
    }
}
