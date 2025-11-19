package com.example.lab_week_11_a.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // Diperlukan untuk coroutine
import com.example.lab_week_11_a.wrappers.DataStoreWrapper
import kotlinx.coroutines.launch

class DataStoreViewModel (private val wrapper: DataStoreWrapper): ViewModel() {

    private val _textLiveData = MutableLiveData<String>()
    val textLiveData: LiveData<String> = _textLiveData

    init {
        // Luncurkan coroutine untuk mengumpulkan (collect) data dari Flow
        viewModelScope.launch {
            wrapper.textFlow.collect {
                // Perbarui LiveData setiap kali ada perubahan di DataStore
                _textLiveData.value = it
            }
        }
    }

    /**
     * Menyimpan teks baru ke DataStore secara asynchronous.
     */
    fun saveText(newText: String) {
        // Luncurkan coroutine untuk memanggil fungsi suspend di Wrapper
        viewModelScope.launch {
            wrapper.saveText(newText)
        }
    }
}