package com.example.map_lab_week10

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.map_lab_week10.viewmodels.TotalViewModel

class MainActivity : AppCompatActivity() {

    // Inisialisasi ViewModel secara Lazy
    private val viewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Ambil nilai terakhir dari ViewModel (agar saat rotate data tetap ada)
        updateText(viewModel.total)

        // 2. Set listener tombol
        findViewById<Button>(R.id.button_increment).setOnClickListener {
            // Panggil fungsi di ViewModel, lalu update UI dengan nilai balikan
            val newTotal = viewModel.incrementTotal()
            updateText(newTotal)
        }
    }

    private fun updateText(total: Int) {
        findViewById<TextView>(R.id.text_total).text = getString(R.string.text_total, total)
    }
}