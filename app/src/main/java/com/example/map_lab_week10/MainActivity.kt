package com.example.map_lab_week10

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.map_lab_week10.database.Total
import com.example.map_lab_week10.database.TotalDatabase
import com.example.map_lab_week10.viewmodels.TotalViewModel

class MainActivity : AppCompatActivity() {

    // Konstanta ID (karena kita cuma simpan 1 row data, ID-nya kita hardcode 1)
    companion object {
        const val ID: Long = 1
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    // Inisialisasi Database
    private val db by lazy { prepareDatabase() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Load data dari database dulu
        initializeValueFromDatabase()

        // 2. Baru setup ViewModel & Listener
        prepareViewModel()
    }

    // Saat aplikasi dipause/ditutup/pindah aplikasi, simpan data ke DB
    override fun onPause() {
        super.onPause()
        // Force unwrap !! aman disini karena kita tahu value pasti ada (min 0)
        // PERINGATAN: Di app beneran, jangan jalankan DB query di Main Thread seperti ini.
        val currentTotal = viewModel.total.value ?: 0
        db.totalDao().update(Total(ID, currentTotal))
    }

    private fun prepareDatabase(): TotalDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TotalDatabase::class.java,
            "total-database"
        ).allowMainThreadQueries().build() // DANGER: Hanya untuk Lab
    }

    private fun initializeValueFromDatabase() {
        // Cek apakah data dengan ID=1 sudah ada?
        val totalList = db.totalDao().getTotal(ID)

        if (totalList.isEmpty()) {
            // Jika belum ada (aplikasi baru diinstall), buat row baru dengan nilai 0
            db.totalDao().insert(Total(ID, 0))
        } else {
            // Jika sudah ada, ambil nilainya dan set ke ViewModel
            viewModel.setTotal(totalList.first().total)
        }
    }

    private fun prepareViewModel() {
        viewModel.total.observe(this) { total ->
            updateText(total)
        }

        findViewById<Button>(R.id.button_increment).setOnClickListener {
            viewModel.incrementTotal()
        }
    }

    private fun updateText(total: Int) {
        findViewById<TextView>(R.id.text_total).text = getString(R.string.text_total, total)
    }
}