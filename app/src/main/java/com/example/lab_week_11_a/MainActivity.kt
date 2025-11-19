package com.example.lab_week_11_a

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_11_a.viewmodels.DataStoreViewModel // Ganti import ViewModel
import com.example.lab_week_11_a.wrappers.DataStoreWrapper

class MainActivity : AppCompatActivity() {

    private lateinit var dataStoreViewModel: DataStoreViewModel // Ganti tipe ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Dapatkan instance DataStoreWrapper dari kelas Application
        val application = application as DataStoreApplication // Ganti tipe Application
        val dataStoreWrapper = application.dataStoreWrapper // Ganti nama instance

        // 2. Buat ViewModel menggunakan Factory kustom
        dataStoreViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                // Mengembalikan instance DataStoreViewModel
                return DataStoreViewModel(dataStoreWrapper) as T
            }
        })[DataStoreViewModel::class.java] // Ganti tipe ViewModel

        // 3. Amati LiveData untuk memperbarui TextView
        dataStoreViewModel.textLiveData.observe(this) { text -> // Ganti nama ViewModel
            val displayText = if (text.isEmpty()) getString(R.string.default_text) else text
            findViewById<TextView>(R.id.activity_main_text_view).text = displayText
        }

        // 4. Setup Listener tombol SIMPAN
        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            val editText = findViewById<EditText>(R.id.activity_main_edit_text)
            val newText = editText.text.toString()

            // Panggil fungsi save di ViewModel
            dataStoreViewModel.saveText(newText)

            // Opsional: Kosongkan EditText setelah disimpan
            editText.text.clear()
        }
    }
}