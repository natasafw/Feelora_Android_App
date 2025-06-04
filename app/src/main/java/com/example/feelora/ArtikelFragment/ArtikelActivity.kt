package com.example.feelora.ArtikelFragment

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.feelora.R

class ArtikelActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artikel)

        // Set WindowInsets untuk handling system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Atur judul di toolbar
        supportActionBar?.title = "Artikel"

        // Inisialisasi tombol back di toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Tangkap informasi dari Intent
        val fragmentType = intent.getIntExtra("FRAGMENT_TYPE", 1)

        // Tentukan fragment mana yang akan ditampilkan
        val fragment: Fragment = when (fragmentType) {
            1 -> ArtikelFragment1()  // Fragment untuk gambar 1
            2 -> ArtikelFragment2()  // Fragment untuk gambar 2
            3 -> ArtikelFragment3()  // Fragment untuk gambar 3
            else -> ArtikelFragment1() // Default fragment
        }

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
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)  // Tambahkan ke backstack agar bisa kembali ke fragment sebelumnya
            .commit()
    }
}
