package com.example.map_lab_week10.viewmodels


import androidx.lifecycle.ViewModel

class TotalViewModel : ViewModel() {
    var total: Int = 0

    // Fungsi ini menambah nilai dan mengembalikan nilai baru
    fun incrementTotal(): Int {
        total++
        return total
    }
}