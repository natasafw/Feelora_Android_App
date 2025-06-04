package com.example.feelora.ListView

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.feelora.ListDetail.ListDetailActivity
import com.example.feelora.R

class ListViewActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView: ListView = findViewById(R.id.list_item)

        // Inisialisasi toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Atur judul di toolbar
        supportActionBar?.title = "Setting"

        // Inisialisasi tombol back di toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val menuList = listOf(
            ListModel(1,"Akun", "Akun 1", R.drawable.ic_person),
            ListModel(2,"Notifikasi", "Aktif", R.drawable.ic_notifikasi),
            ListModel(3,"Bahasa", "Bahasa Indonesia", R.drawable.ic_language),
            ListModel(4,"Tampilan", "Default", R.drawable.ic_apperance),
            ListModel(5,"Tentang Aplikasi", "feelora v.1.20.i", R.drawable.ic_app),
        )

        val adapter = ListAdapter(this, menuList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = menuList[position]
            val intent = Intent(this, ListDetailActivity::class.java)
            // Pass only the ID to ListDetailActivity
            intent.putExtra("item_id", selectedItem.id)
            startActivity(intent)
        }
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
}