package com.example.lab_week_11_a // Ganti dengan nama package Anda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_11_a.viewmodels.PreferenceViewModel
import com.example.lab_week_11_a.viewmodels.PreferenceWrapper

class MainActivity : AppCompatActivity() {

    private lateinit var preferenceViewModel: PreferenceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Dapatkan instance SharedPreferences dari kelas Application
        val application = application as PreferenceApplication
        val sharedPrefs = application.sharedPreferences

        // 2. Buat instance Wrapper
        val preferenceWrapper = PreferenceWrapper(this)

        // 3. Buat ViewModel menggunakan Factory kustom untuk menyuntikkan Wrapper
        preferenceViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                // Mengembalikan instance PreferenceViewModel dengan Wrapper sebagai dependency
                return PreferenceViewModel(preferenceWrapper) as T
            }
        })[PreferenceViewModel::class.java]

        // 4. Amati LiveData untuk memperbarui TextView
        preferenceViewModel.textLiveData.observe(this) { text ->
            // Gunakan default string jika text kosong (hanya untuk tampilan)
            val displayText = if (text.isEmpty()) getString(R.string.default_text) else text
            findViewById<TextView>(R.id.activity_main_text_view).text = displayText
        }

        // 5. Setup Listener tombol
        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            val editText = findViewById<EditText>(R.id.activity_main_edit_text)
            val newText = editText.text.toString()

            // Panggil fungsi save di ViewModel
            preferenceViewModel.saveText(newText)

            // Opsional: Kosongkan EditText setelah disimpan
            editText.text.clear()
        }
    }
}