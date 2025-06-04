package com.example.feelora.ListDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.feelora.R
import com.example.feelora.databinding.ActivityListDetailBinding

class ListDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListDetailBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding menggunakan inflate()
        binding = ActivityListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        // Inisialisasi WindowInsets untuk penyesuaian padding
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi toolbar menggunakan binding
        setSupportActionBar(binding.toolbar)

        // Inisialisasi tombol back di toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Tangkap informasi dari Intent
        val fragmentType = intent.getIntExtra("item_id", 1)

        // Tentukan fragment mana yang akan ditampilkan
        val fragment: Fragment = when (fragmentType) {
            1 -> AkunFragment()
            2 -> NotifikasiFragment()
            3 -> BahasaFragment()
            4 -> TampilanFragment()
            5 -> TentangFragment()
            else -> AkunFragment() // Default fragment
        }

        // Replace fragment dengan pilihan
        replaceFragment(fragment)
    }

    // Fungsi untuk menangani tombol back pada toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()  // Tutup activity ketika tombol back ditekan
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Fungsi untuk mengganti fragment secara dinamis
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)  // Tambahkan ke backstack agar bisa kembali ke fragment sebelumnya
            .commit()
    }
}
