package com.example.lab_week_10.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TotalViewModel : ViewModel() {
    // _total adalah Mutable (bisa diubah isinya), private agar tidak diubah sembarangan dari luar
    private val _total = MutableLiveData<Int>()

    // total adalah LiveData (hanya bisa dibaca/diobserve), publik untuk diakses UI
    val total: LiveData<Int> = _total

    init {
        // Set nilai awal 0
        _total.postValue(0)
    }

    fun incrementTotal() {
        // Ambil nilai sekarang, tambah 1, lalu post lagi
        _total.postValue(_total.value?.plus(1))
    }
}